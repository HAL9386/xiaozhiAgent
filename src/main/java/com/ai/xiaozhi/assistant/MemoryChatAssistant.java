package com.ai.xiaozhi.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

// 当AIService由多个组件（大模型，聊天记忆，等）组成的时候，我们就可以称他为智能体了
@AiService(
  wiringMode = AiServiceWiringMode.EXPLICIT,
  chatModel = "qwenChatModel",
  chatMemory = "chatMemory"
)
public interface MemoryChatAssistant {
  String chat(String userMessage);
}
