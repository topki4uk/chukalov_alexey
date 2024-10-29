package enrichments;

import user.MyUserRepository;
import user.User;

import java.util.HashMap;
import java.util.Map;

public class MSISDNEnrichment implements Enrichment {
  private final MyUserRepository repository;

  public MSISDNEnrichment(MyUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Map<String, String> enrich(Map<String, String> input) {
    Map<String, String> result = new HashMap<>(input);
    String msisdn = input.get("msisdn");

    if (msisdn == null) {
      return result;
    }

    User user = repository.findByMsisdn(msisdn);

    if (user == null) {
      return result;
    }

    result.put("firstName", user.firstName());
    result.put("lastName", user.lastName());

    return result;
  }

  @Override
  public EnrichmentType getEnrichmentType() {
    return EnrichmentType.MSISDN;
  }
}
