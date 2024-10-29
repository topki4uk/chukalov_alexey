package org.example;

import enrichments.EnrichmentType;
import enrichments.MSISDNEnrichment;
import message.Message;
import service.EnrichmentService;
import user.MyUserRepository;
import user.User;

import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    Map<String, String> input = new HashMap<>();
    input.put("action", "button_click");
    input.put("page", "book_card");
    input.put("msisdn", "88005553535");

    Message message = new Message(input, EnrichmentType.MSISDN);
    User user = new User("Oleg", "Gazmanov");

    EnrichmentService service = new EnrichmentService();
    MyUserRepository repo = new MyUserRepository();
    repo.updateUserByMsisdn(message.getContent().get("msisdn"), user);

    service.addEnrichment(EnrichmentType.MSISDN, new MSISDNEnrichment(repo));
    Message newMessage = service.enrich(message);

    System.out.println(newMessage.getContent());
  }
}
