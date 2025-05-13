package com.ai.xiaozhi;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LLMTest {
  @Value("${openai.api.key}")
  private String apikey;
  @Test
  public void testGPT() {
    OpenAiChatModel model = OpenAiChatModel.builder()
      .baseUrl("http://langchain4j.dev/demo/openai/v1")
      .apiKey(apikey)
      .modelName("gpt-4o-mini")
      .build();
    String answer = model.chat("你好");
    System.out.println(answer);
  }

  @Autowired
  private OpenAiChatModel openAiChatModel;
  @Test
  public void testGPTConfig() {
    String answer = openAiChatModel.chat("你好");
    System.out.println(answer);
  }
}
