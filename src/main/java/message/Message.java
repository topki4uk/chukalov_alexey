package message;

import enrichments.Enrichment;
import enrichments.EnrichmentType;

import java.util.HashMap;
import java.util.Map;

public class Message {

  private Map<String, String> content;
  private final EnrichmentType enrichmentType;

  public Message(Map<String, String> input, EnrichmentType type) {
    content = new HashMap<>(input);
    enrichmentType = type;
  }

  public Map<String, String> getContent() {
    return content;
  }

  public void updateContent(Enrichment enrichment) {
    content = enrichment.enrich(content);
  }

  public EnrichmentType getEnrichmentType() {
    return enrichmentType;
  }
}
