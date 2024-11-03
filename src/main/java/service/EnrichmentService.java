package service;

import enrichments.Enrichment;
import enrichments.EnrichmentType;
import message.Message;

import java.util.concurrent.*;

public class EnrichmentService {
  ConcurrentHashMap<EnrichmentType, Enrichment> enrichments = new ConcurrentHashMap<>();

  public void addEnrichment(EnrichmentType type, Enrichment enrichment) {
    enrichments.put(type, enrichment);
  }

  public Message enrich(Message message) {
    if (message == null) {
      return null;
    }

    Executor executor = Executors.newFixedThreadPool(10);
    Message newMessage;

    CompletableFuture<Message> future = CompletableFuture
              .supplyAsync(() -> {
                message.updateContent(
                        enrichments.get(message.getEnrichmentType())
                );
                return message;
                }, executor);

    try {
      newMessage = future.get(3, TimeUnit.SECONDS);
    } catch (ExecutionException | InterruptedException | TimeoutException e) {
      throw new RuntimeException(e);
    } finally {
      future.complete(message);
    }

    return newMessage;
  }
}
