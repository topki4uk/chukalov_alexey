import enrichments.Enrichment;
import enrichments.EnrichmentType;
import enrichments.MSISDNEnrichment;
import service.EnrichmentService;
import message.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import user.MyUserRepository;
import user.User;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
  static final String MSISDN = "89162455748";
  static final User USER = new User("Artur", "Kulapin");
  static final String FAVORITE = "algosiki";
  static final String COURSE = "AaSD";
  static Message message;
  static EnrichmentService service;

  @BeforeAll
  static void beforeAll() {
    MyUserRepository repo = new MyUserRepository();
    repo.updateUserByMsisdn(MSISDN, USER);
    Enrichment enrichment = new MSISDNEnrichment(repo);

    service = new EnrichmentService();
    service.addEnrichment(EnrichmentType.MSISDN, enrichment);

    message = new Message(
            Map.of("msisdn", MSISDN,
                    "favorite", FAVORITE,
                    "course", COURSE
            ), EnrichmentType.MSISDN
    );
  }

  @Test
  void testEnrichMessage() {
    Message enriched = service.enrich(message);
    assertEquals(USER.firstName(), enriched.getContent().get("firstName"));
    assertEquals(USER.lastName(), enriched.getContent().get("lastName"));
    assertEquals(MSISDN, enriched.getContent().get("msisdn"));
    assertEquals(FAVORITE, enriched.getContent().get("favorite"));
    assertEquals(COURSE, enriched.getContent().get("course"));
  }
}