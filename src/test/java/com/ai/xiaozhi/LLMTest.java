package com.ai.xiaozhi;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
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
    String answer = openAiChatModel.chat("你是谁");
    System.out.println(answer);
  }

  @Autowired
  private OllamaChatModel ollamaChatModel;
  @Test
  public void testOllama() {
    String answer = ollamaChatModel.chat("你是谁");
    System.out.println(answer);
  }

  @Autowired
  private QwenChatModel qwenChatModel;
  @Test
  public void testQwen() {
    String answer = qwenChatModel.chat("你是谁");
    System.out.println(answer);
  }
}
