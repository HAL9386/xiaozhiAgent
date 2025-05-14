package com.ai.xiaozhi.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
  wiringMode = AiServiceWiringMode.EXPLICIT,
  chatModel = "qwenChatModel",
  chatMemoryProvider = "chatMemoryProvider"
)
public interface SeparateChatAssistant {
  String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
