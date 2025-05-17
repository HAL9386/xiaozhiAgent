package com.ai.xiaozhi.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
  wiringMode = AiServiceWiringMode.EXPLICIT,
//  chatModel = "qwenChatModel",
  streamingChatModel = "qwenStreamingChatModel",
  chatMemoryProvider = "chatMemoryProviderXiaozhi",
  tools = {
    "appointmentTools",
  },
  contentRetriever = "contentRetrieverXiaozhiPinecone"
)
public interface XiaozhiAgent {
  @SystemMessage(fromResource = "XiaozhiSystemPrompt.txt")
//  String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
  Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
