import enrichments.Enrichment;
import enrichments.EnrichmentType;
import enrichments.MSISDNEnrichment;
import message.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.EnrichmentService;
import user.MyUserRepository;
import user.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrencyApplicationTest {
  static final String MSISDN1 = "88001234567";
  static final String MSISDN2 = "88005553535";
  static final String MSISDN3 = "88005357638";
  static final String[] MSISDNS = {MSISDN1, MSISDN2, MSISDN3};

  // MSU SQUAD
  static final User USER1 = new User("Oleg", "Bankovskiy");
  static final User USER2 = new User("Fedor", "Bondar");
  static final User USER3 = new User("Egor", "Lankin");
  static final User[] USERS = {USER1, USER2, USER3};

  static CopyOnWriteArrayList<Message> messages;
  static EnrichmentService service;

  @BeforeAll
  static void beforeAll() {
    MyUserRepository repo = new MyUserRepository();
    repo.updateUserByMsisdn(MSISDN1, USER1);
    repo.updateUserByMsisdn(MSISDN2, USER2);
    repo.updateUserByMsisdn(MSISDN3, USER3);
    Enrichment enrichment = new MSISDNEnrichment(repo);

    service = new EnrichmentService();
    service.addEnrichment(EnrichmentType.MSISDN, enrichment);
    messages = new CopyOnWriteArrayList<>(
            List.of(
                    new Message(Map.of("msisdn", MSISDN1), EnrichmentType.MSISDN),
                    new Message(Map.of("msisdn", MSISDN2), EnrichmentType.MSISDN),
                    new Message(Map.of("msisdn", MSISDN3), EnrichmentType.MSISDN)
            ));
  }

  @Test
  void testEnrichConcurrency() throws InterruptedException {
    CopyOnWriteArrayList<Message> enrichmentResults = new CopyOnWriteArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    CountDownLatch latch = new CountDownLatch(3);

    for (int i = 0; i < 3; ++i) {
      int fi = i;
      executorService.submit(() -> {
        enrichmentResults.add(service.enrich(messages.get(fi)));
        latch.countDown();
      });
    }
    latch.await();

    for (int i = 0; i < 3; ++i) {
      assertEquals(USERS[i].firstName(), enrichmentResults.get(i).getContent().get("firstName"));
      assertEquals(USERS[i].lastName(), enrichmentResults.get(i).getContent().get("lastName"));
      assertEquals(MSISDNS[i], enrichmentResults.get(i).getContent().get("msisdn"));
    }
    executorService.shutdown();
  }
}
