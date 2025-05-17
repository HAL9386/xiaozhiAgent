package com.ai.xiaozhi.config;

import com.ai.xiaozhi.store.MongoChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class XiaozhiAgentConfig {
  @Autowired
  private MongoChatMemoryStore mongoChatMemoryStore;

  @Autowired
  private EmbeddingModel embeddingModel;

  @Autowired
  private EmbeddingStore<TextSegment> embeddingStore;

  @Bean
  public ChatMemoryProvider chatMemoryProviderXiaozhi() {
    return memoryId -> MessageWindowChatMemory.builder()
        .id(memoryId)
        .maxMessages(100)
        .chatMemoryStore(mongoChatMemoryStore)
        .build();
  }

//  @Bean
  public ContentRetriever contentRetrieverXiaozhi() {
    List<Document> documents = FileSystemDocumentLoader.loadDocuments("D:/documnt/uhgvgu/knowledge");
    InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
    EmbeddingStoreIngestor.ingest(documents, embeddingStore);
    return EmbeddingStoreContentRetriever.from(embeddingStore);
  }

  @Bean
  public ContentRetriever contentRetrieverXiaozhiPinecone() {
    return EmbeddingStoreContentRetriever.builder()
      .embeddingModel(embeddingModel)
      .embeddingStore(embeddingStore)
      .maxResults(1)
      .minScore(0.7)
      .build();
  }
}
