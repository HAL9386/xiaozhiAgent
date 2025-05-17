package com.ai.xiaozhi;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmbeddingTest {
  @Autowired
  private EmbeddingModel embeddingModel;

  @Autowired
  private EmbeddingStore<TextSegment> embeddingStore;

  /**
   * 测试向量生成和存储到pinecone
   */
  @Test
  public void testPineconeEmbedding() {
    System.out.println(System.getenv("PINECONE_API_KEY"));
    TextSegment textSegment1 = TextSegment.from("我喜欢踢足球");
    Embedding embedding1 = embeddingModel.embed(textSegment1).content();
    embeddingStore.add(embedding1, textSegment1);

    TextSegment textSegment2 = TextSegment.from("今天天气很好");
    Embedding embedding2 = embeddingModel.embed(textSegment2).content();
    embeddingStore.add(embedding2, textSegment2);
  }

  /**
   * pinecone 向量相似度匹配
   */
  @Test
  public void embeddingSearch() {
    //提问，并将问题转成向量数据
    Embedding queryEmbedding = embeddingModel.embed("你最喜欢的运动是什么？").content();
    //创建搜索请求对象
    EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
      .queryEmbedding(queryEmbedding)
      .maxResults(1) //匹配最相似的一条记录
      // .minScore(0.8)
      .build();
    // 根据搜索请求 searchRequest 在向量存储中进行相似度搜索
    EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
    // searchResult.matches()：获取搜索结果中的匹配项列表。
    // .get(0)：从匹配项列表中获取第一个匹配项
    EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);
    //获取匹配项的相似度得分
    System.out.println(embeddingMatch.score()); // 0.8144288515898701
    //返回文本结果
    System.out.println(embeddingMatch.embedded().text());
  }

  @Test
  public void testUploadKnowledgeLib() {
    List<Document> documents = FileSystemDocumentLoader.loadDocuments("D:/documnt/uhgvgu/knowledge");
    EmbeddingStoreIngestor.builder()
      .embeddingStore(embeddingStore)
      .embeddingModel(embeddingModel)
      .build()
      .ingest(documents);
  }

}