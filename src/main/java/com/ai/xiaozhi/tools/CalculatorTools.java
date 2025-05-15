package com.ai.xiaozhi.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import dev.langchain4j.service.MemoryId;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTools {
  /**
   * 求和
   *
   * @param a 第一个数
   * @param b 第二个数
   * @return  返回a+b的结果
   */
  @Tool(name = "加法运算", value = "将两个参数a和b相加并返回运算结果")
  double sum(
    @ToolMemoryId int memoryId,
    @P(value = "加数1", required = true) double a,
    @P(value = "加数2", required = true) double b
  ) {
    System.out.println("调用了求和方法 memoryId: " + memoryId);
    return a + b;
  }

  /**
   * 求平方根
   *
   * @param a 待求平方根的数
   * @return  返回a的平方根
   */
  @Tool(name = "求平方根", value = "计算并返回参数a的平方根")
  double squareRoot(
    @ToolMemoryId int memoryId,
    @P(value = "开平方根的数", required = true) double a
  ) {
    System.out.println("调用了求平方根方法 memoryId: " + memoryId);
    return Math.sqrt(a);
  }
}
