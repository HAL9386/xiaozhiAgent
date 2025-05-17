package com.ai.xiaozhi.tools;

import com.ai.xiaozhi.entity.Appointment;
import com.ai.xiaozhi.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppointmentTools {
  @Autowired
  private AppointmentService appointmentService;

  @Tool(name="预约挂号", value = "让用户提供所有必要的预约信息（姓名、科室名称、日期、时间（上午或下午）、医生名称可选），" +
    "如果信息不完善不能预约，提示用户补充信息，如果用户没有提供具体的医生姓名，请从向量存储中找到一位医生。" +
    "根据参数，先执行工具方法queryDepartment查询科室是否可预约，并告知用户。" +
    "然后提示用户确认预约信息，确认后再进行预约。")
  public String bookAppointment(Appointment appointment) {
    log.info("预约挂号：{}", appointment);
    Appointment appointmentRecord = appointmentService.getOne(appointment);
    if (appointmentRecord != null) {
      return "您在相同的科室和时间已经有预约记录，无法再次预约。";
    }
    appointment.setId(null); // 防止大模型出现幻觉设置了id
    if (appointmentService.save(appointment)) {
      return "预约成功！";
    } else {
      return "预约失败，请稍后再试。";
    }
  }

  @Tool(name="取消预约挂号", value = "根据用户信息参数，查询预约是否存在，" +
    "如果存在则从查询出的预约记录中提取出id字段的值，传递给删除方法并删除预约记录，成功则返回取消预约成功，否则返回取消预约失败")
  public String cancelAppointment(Appointment appointment) {
    log.info("取消预约挂号：{}", appointment);
    Appointment appointmentRecord = appointmentService.getOne(appointment);
    if (appointmentRecord == null) {
      return "您没有预约记录，请核对预约科室和时间。";
    }
    if (appointmentService.removeById(appointmentRecord.getId())) {
      return "取消预约成功！";
    } else {
      return "取消预约失败，请稍后再试。";
    }
  }

  @Tool(name = "查询是否有号源", value="根据科室名称，日期，时间和医生查询是否有号源，并返回给用户")
  public boolean queryDepartment(
    @P("科室名称")                           String departmentName,
    @P("日期")                               String date,
    @P("时间，可选值：上午、下午")           String time,
    @P(value = "医生名称", required = false) String doctorName
  ) {
    log.info("查询是否有号源：{}，{}，{}，{}", departmentName, date, time, doctorName);
    System.out.println("查询是否有号源");
    System.out.println("科室名称：" + departmentName);
    System.out.println("日期：" + date);
    System.out.println("时间：" + time);
    System.out.println("医生名称：" + doctorName);
    // TODO 维护医生的排班信息：
    // 如果没有指定医生名字，则根据其他条件查询是否有可以预约的医生（有返回true，否则返回false）；
    // 如果指定了医生名字，则判断医生是否有排班（没有排版返回false）
    // 如果有排班，则判断医生排班时间段是否已约满（约满返回false，有空闲时间返回true）
    return true;
  }
}
