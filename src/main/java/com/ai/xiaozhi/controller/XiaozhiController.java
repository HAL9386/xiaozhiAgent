package com.ai.xiaozhi.controller;

import com.ai.xiaozhi.assistant.XiaozhiAgent;
import com.ai.xiaozhi.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "小智医疗")
@RestController
@RequestMapping("/xiaozhi")
public class XiaozhiController {
  @Autowired
  private XiaozhiAgent xiaozhiAgent;

  @Operation(summary = "对话")
  @PostMapping("/chat")
  public String chat(@RequestBody ChatForm chatForm) {
    return xiaozhiAgent.chat(chatForm.getMemoryId(), chatForm.getUserMessage());
  }
}
