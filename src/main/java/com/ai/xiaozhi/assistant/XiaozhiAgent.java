package com.ai.xiaozhi.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
  wiringMode = AiServiceWiringMode.EXPLICIT,
  chatModel = "qwenChatModel",
  chatMemoryProvider = "chatMemoryProviderXiaozhi",
  tools = {
    "appointmentTools",
  }
)
public interface XiaozhiAgent {
  @SystemMessage(fromResource = "XiaozhiSystemPrompt.txt")
  String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
