package com.ai.xiaozhi.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
  wiringMode = AiServiceWiringMode.EXPLICIT,
  chatModel = "qwenChatModel",
  chatMemory = "chatMemory"
)
public interface MemoryChatAssistant {
  String chat(String userMessage);
}
