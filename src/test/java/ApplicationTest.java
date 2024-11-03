import enrichments.Enrichment;
import enrichments.EnrichmentType;
import enrichments.MsisdnEnrichment;
import service.EnrichmentService;
import message.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import user.ConcreteUserRepository;
import user.User;

import java.util.Map;
import user.UserFields;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
  private enum ExtendedUserFields {
    FAVORITE("favorite"),
    COURSE("course");

    private final String fieldName;


    ExtendedUserFields(String fieldName) {
      this.fieldName = fieldName;
    }

    public String getFieldName() {
      return fieldName;
    }
  }

  static final String MSISDN = "89162455748";
  static final User USER = new User("Artur", "Kulapin");
  static final String FAVORITE = "algosiki";
  static final String COURSE = "AaSD";
  static Message message;
  static EnrichmentService service;

  @BeforeAll
  static void beforeAll() {
    ConcreteUserRepository repo = new ConcreteUserRepository();
    repo.updateUserByMsisdn(MSISDN, USER);
    Enrichment enrichment = new MsisdnEnrichment(repo);

    service = new EnrichmentService();
    service.addEnrichment(EnrichmentType.MSISDN, enrichment);

    message = new Message(
            Map.of(EnrichmentType.MSISDN.getFieldName(), MSISDN,
                    ExtendedUserFields.FAVORITE.getFieldName(), FAVORITE,
                ExtendedUserFields.COURSE.getFieldName(), COURSE
            ), EnrichmentType.MSISDN
    );
  }

  @Test
  void testEnrichMessage() {
    Message enriched = service.enrich(message);
    assertEquals(USER.firstName(), enriched.getContent().get(UserFields.FIRST_NAME.getFieldName()));
    assertEquals(USER.lastName(), enriched.getContent().get(UserFields.LAST_NAME.getFieldName()));
    assertEquals(MSISDN, enriched.getContent().get(EnrichmentType.MSISDN.getFieldName()));
    assertEquals(FAVORITE, enriched.getContent().get(ExtendedUserFields.FAVORITE.getFieldName()));
    assertEquals(COURSE, enriched.getContent().get(ExtendedUserFields.COURSE.getFieldName()));
  }
}