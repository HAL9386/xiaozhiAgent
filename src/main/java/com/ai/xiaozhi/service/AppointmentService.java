package com.ai.xiaozhi.service;

import com.ai.xiaozhi.entity.Appointment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService extends IService<Appointment> {
  Appointment getOne(Appointment appointment);
}
