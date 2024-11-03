package enrichments;

import user.ConcreteUserRepository;
import user.User;

import java.util.HashMap;
import java.util.Map;
import user.UserFields;

public class MsisdnEnrichment implements Enrichment {
  private final ConcreteUserRepository repository;

  public MsisdnEnrichment(ConcreteUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Map<String, String> enrich(Map<String, String> input) {
    if (input == null) {
      return null;
    }

    Map<String, String> result = new HashMap<>(input);
    String msisdn = input.get(EnrichmentType.MSISDN.getFieldName());

    if (msisdn == null) {
      return result;
    }

    User user = repository.findByMsisdn(msisdn);

    if (user == null) {
      return result;
    }

    result.put(UserFields.FIRST_NAME.getFieldName(), user.firstName());
    result.put(UserFields.LAST_NAME.getFieldName(), user.lastName());

    return result;
  }

  @Override
  public EnrichmentType getEnrichmentType() {
    return EnrichmentType.MSISDN;
  }
}
