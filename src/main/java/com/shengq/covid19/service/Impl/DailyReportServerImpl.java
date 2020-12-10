package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.AbnormalPerson;
import com.shengq.covid19.dao.ClientUser;
import com.shengq.covid19.dao.DailyReport;
import com.shengq.covid19.mapper.AbnormalPersonMapper;
import com.shengq.covid19.mapper.ClientUserMapper;
import com.shengq.covid19.mapper.DailyReportMapper;
import com.shengq.covid19.service.DailyReportService;
import com.shengq.covid19.vo.DailyReportVo;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
@Slf4j
@Service
public class DailyReportServerImpl implements DailyReportService {
    final DailyReportMapper dailyReportMapper;
    final  ClientUserMapper clientUserMapper;
    final AbnormalPersonMapper abnormalPersonMapper;

    public DailyReportServerImpl(DailyReportMapper dailyReportMapper, ClientUserMapper clientUserMapper, AbnormalPersonMapper abnormalPersonMapper) {
        this.dailyReportMapper = dailyReportMapper;
        this.clientUserMapper = clientUserMapper;
        this.abnormalPersonMapper = abnormalPersonMapper;
    }

    @Override
    public List<DailyReport> findInfoById(String userid) {
        return dailyReportMapper.findById(userid);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int addNewRecording(DailyReportVo info) {
        boolean istouch = info.isIstouch();
        float temperature = Float.parseFloat(info.getTemperature());
        String address = info.getAddress();
        ClientUser clientUser = clientUserMapper.findById(info.getUserid());
        int status = 0;
        boolean isAbnormal = false;
        // 异常接触分析
        if (istouch){
            clientUser.setIstouch(1);
            isAbnormal = true;
            status++;
        }
        // 异常温度分析
        if (temperature>38.0||temperature<35.5){
            clientUser.setIstemperature(1);
            isAbnormal = true;
            status++;
        }
        // TODO 地区分析未完成
        if ("北京".equals(address)){
            clientUser.setIsarea(1);
            isAbnormal = true;
            status++;
        }
        if (isAbnormal){
            // 更新用户异常信息
            clientUserMapper.updateClientUserStatus(clientUser.getUserid(),clientUser.getIstouch(),
                    clientUser.getIsarea(),clientUser.getIstemperature());
            // 存入异常人员表
            abnormalPersonMapper.insert(clientUser.getUserid(),clientUser.getMobile(),info.getAddress(),info.getAddress(),
                    0,info.getRemark());
            log.info("记录一个异常人员信息:"+clientUser);
        }
        DailyReport dailyReport = new DailyReport();
        dailyReport.setUserid(info.getUserid());
        dailyReport.setAddress(info.getAddress());
        dailyReport.setTemperature(info.getTemperature());
        dailyReport.setRemark(info.getRemark());
        dailyReport.setStatus(status);
        return dailyReportMapper.insert(dailyReport);
    }

    @Override
    public List<DailyReport> getAllRecording() {
        return dailyReportMapper.findAlldailyreport();
    }
}
