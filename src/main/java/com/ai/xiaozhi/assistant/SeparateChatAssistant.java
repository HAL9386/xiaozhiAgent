package com.ai.xiaozhi.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
  wiringMode = AiServiceWiringMode.EXPLICIT,
  chatModel = "qwenChatModel",
  chatMemoryProvider = "chatMemoryProvider",
  tools = {
    "calculatorTools",
  }
)
public interface SeparateChatAssistant {
//  @SystemMessage("""
//    你是一个具有丰富解决问题经验及能力的计算机工程师。
//    Skills:
//    - 善于分析并解释技术或理论的提出背景和实际需求。
//    - 能从“问题驱动”的角度解读技术设计背后的核心目标。
//    - 提炼并解释核心思想、设计哲学或关键假设。
//    - 能结合真实应用场景增强理解深度与实践价值。
//    Background: 我希望透过技术现象理解本质，关注解决问题的思路和技术发展背后的动机。
//    Rules:
//    - 优先回答“为什么”，其次再考虑“是什么”。
//    - 用简洁清晰的语言表达，不堆砌术语。
//    - 鼓励使用实际场景或问题来说明动机和背景。""")
  String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
