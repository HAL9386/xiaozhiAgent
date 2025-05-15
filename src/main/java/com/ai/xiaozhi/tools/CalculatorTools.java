package com.ai.xiaozhi.tools;

import dev.langchain4j.agent.tool.Tool;
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
  @Tool
  double sum(double a, double b) {
    System.out.println("调用了求和方法");
    return a + b;
  }

  /**
   * 求平方根
   *
   * @param a 待求平方根的数
   * @return  返回a的平方根
   */
  @Tool
  double squareRoot(double a) {
    System.out.println("调用了求平方根方法");
    return Math.sqrt(a);
  }
}
