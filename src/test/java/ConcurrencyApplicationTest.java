import enrichments.Enrichment;
import enrichments.EnrichmentType;
import enrichments.MsisdnEnrichment;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import message.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.EnrichmentService;
import user.ConcreteUserRepository;
import user.User;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrencyApplicationTest {
  static final String MSISDN1 = "88001234567";
  static final String MSISDN2 = "88005553535";
  static final String MSISDN3 = "88005357638";

  // MSU SQUAD
  static final User USER1 = new User("Oleg", "Bankovskiy");
  static final User USER2 = new User("Fedor", "Bondar");
  static final User USER3 = new User("Egor", "Lankin");
  static final Map<String, User> USERS = new HashMap<>(
      Map.of(
          MSISDN1, USER1,
          MSISDN2, USER2,
          MSISDN3, USER3
      )
  );

  static Map<String, Message> messages;
  static EnrichmentService service;

  @BeforeAll
  static void beforeAll() {
    ConcreteUserRepository repo = new ConcreteUserRepository();
    repo.updateUserByMsisdn(MSISDN1, USER1);
    repo.updateUserByMsisdn(MSISDN2, USER2);
    repo.updateUserByMsisdn(MSISDN3, USER3);
    Enrichment enrichment = new MsisdnEnrichment(repo);

    service = new EnrichmentService();
    service.addEnrichment(EnrichmentType.MSISDN, enrichment);
    messages = new ConcurrentHashMap<>(
            Map.of(
                MSISDN1, new Message(
                    Map.of(EnrichmentType.MSISDN.getFieldName(), MSISDN1), EnrichmentType.MSISDN),
                MSISDN2, new Message(
                    Map.of(EnrichmentType.MSISDN.getFieldName(), MSISDN2), EnrichmentType.MSISDN),
                MSISDN3, new Message(
                    Map.of(EnrichmentType.MSISDN.getFieldName(), MSISDN3), EnrichmentType.MSISDN)));
  }

  @Test
  void testEnrichConcurrency() throws InterruptedException {
    Map<String, Message> enrichmentResults = new ConcurrentHashMap<>();
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    CountDownLatch latch = new CountDownLatch(3);

    for (String msisdn : messages.keySet()) {
      executorService.submit(() -> {
        Message message = messages.get(msisdn);
        enrichmentResults.put(msisdn, service.enrich(message));
        latch.countDown();
      });
    }
    latch.await();

    for (String msisdn : enrichmentResults.keySet()) {
      assertEquals(USERS.get(msisdn).firstName(), enrichmentResults.get(msisdn).getContent().get("firstName"));
      assertEquals(USERS.get(msisdn).lastName(), enrichmentResults.get(msisdn).getContent().get("lastName"));
      assertEquals(msisdn, enrichmentResults.get(msisdn).getContent().get("msisdn"));
    }
    executorService.shutdown();
  }
}
