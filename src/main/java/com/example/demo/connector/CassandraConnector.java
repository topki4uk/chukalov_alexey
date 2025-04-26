package com.example.demo.connector;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.Map;

@Configuration
public class CassandraConnector {

  @Bean
  public CqlSession createSession(CqlSessionBuilder sessionBuilder) {
    InetSocketAddress address = InetSocketAddress.createUnresolved("127.0.0.1", 9042);

    sessionBuilder = sessionBuilder.addContactPoint(address);
    sessionBuilder.withLocalDatacenter("datacenter1");
    sessionBuilder.withKeyspace((CqlIdentifier) null);

    CqlSession session = sessionBuilder.build();

    SimpleStatement statement = SchemaBuilder.createKeyspace("hw6")
        .ifNotExists()
        .withNetworkTopologyStrategy(Map.of("datacenter1", 1))
        .build();
    session.execute(statement);

    session.execute("""
        create keyspace if not exists hw6 with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
        """);

    session.execute("""
            CREATE TABLE IF NOT EXISTS hw6.user_audit (
                user_id UUID,
                event_time TIMESTAMP,
                event_type TEXT,
                event_details TEXT,
                PRIMARY KEY ((user_id), event_time)
            ) WITH CLUSTERING ORDER BY (event_time DESC)
               AND default_time_to_live = 2592000;
            """);

    session.execute("""
            CREATE TABLE IF NOT EXISTS hw6.outbox (
                user_id UUID,
                event_time TIMESTAMP,
                event_type TEXT,
                event_details TEXT,
                PRIMARY KEY ((user_id), event_time)
            ) WITH CLUSTERING ORDER BY (event_time DESC);
            """);


    return sessionBuilder
        .withKeyspace("hw6")
        .build();
  }
}
