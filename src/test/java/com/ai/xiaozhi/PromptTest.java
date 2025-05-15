package com.ai.xiaozhi;

import com.ai.xiaozhi.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {
  @Autowired
  private SeparateChatAssistant separateChatAssistant;

  @Test
  public void testSystemPrompt() {
//    String answer = separateChatAssistant.chat(3, "解释一下进程和线程");
//    String answer = separateChatAssistant.chat(3, "那么协程呢");
    String answer = separateChatAssistant.chat(3, "比较上述三个概念");
    System.out.println(answer);
  }
}
