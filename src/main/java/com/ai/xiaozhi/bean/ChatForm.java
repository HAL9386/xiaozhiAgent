package com.ai.xiaozhi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatForm {
  private Long memoryId;
  private String userMessage;
}
