package com.ai.xiaozhi.service.impl;

import com.ai.xiaozhi.entity.Appointment;
import com.ai.xiaozhi.mapper.AppointmentMapper;
import com.ai.xiaozhi.service.AppointmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {
  /**
   * 查询预约是否存在
   * @param appointment 预约信息
   * @return 预约信息
   */
  @Override
  public Appointment getOne(Appointment appointment) {
    LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
    queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard());
    queryWrapper.eq(Appointment::getDepartment, appointment.getDepartment());
    queryWrapper.eq(Appointment::getDate, appointment.getDate());
    queryWrapper.eq(Appointment::getTime, appointment.getTime());
    Appointment appointmentDB = baseMapper.selectOne(queryWrapper);
    return appointmentDB;
  }
}
