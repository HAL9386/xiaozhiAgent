package com.ai.xiaozhi.config;

import com.ai.xiaozhi.store.MongoChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XiaozhiAgentConfig {
  @Autowired
  private MongoChatMemoryStore mongoChatMemoryStore;

  @Bean
  public ChatMemoryProvider chatMemoryProviderXiaozhi() {
    return memoryId -> MessageWindowChatMemory.builder()
        .id(memoryId)
        .maxMessages(100)
        .chatMemoryStore(mongoChatMemoryStore)
        .build();
  }
}
