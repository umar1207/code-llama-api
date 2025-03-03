package api.codellama.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeLlamaService {
    private static final String API_URL = "https://api.together.xyz/v1/chat/completions";
    private static final String API_KEY = "api-key";

    public String generateCode(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "meta-llama/Llama-2-7b-chat-hf");
        requestBody.put("max_tokens", 100);
        requestBody.put("temperature", 0.7);
        requestBody.put("top_p", 0.7);
        requestBody.put("top_k", 50);
        requestBody.put("repetition_penalty", 1);
        requestBody.put("stop", List.of("[/INST]", "</s>"));
        requestBody.put("stream", false); // Set to true if streaming is required

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", "assume you are a senior software dev, review this diff code from a pr for me. Provide only the review comment. Keep it short and simple. Nothing else" + prompt));
        requestBody.put("messages", messages);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
