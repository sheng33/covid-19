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
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class DailyReportServerImpl implements DailyReportService {
    final DailyReportMapper dailyReportMapper;
    final  ClientUserMapper clientUserMapper;
    final AbnormalPersonMapper abnormalPersonMapper;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");

    public DailyReportServerImpl(DailyReportMapper dailyReportMapper, ClientUserMapper clientUserMapper, AbnormalPersonMapper abnormalPersonMapper) {
        this.dailyReportMapper = dailyReportMapper;
        this.clientUserMapper = clientUserMapper;
        this.abnormalPersonMapper = abnormalPersonMapper;
    }

    @Override
    public List<DailyReport> findInfoById(Integer userid) {
        return dailyReportMapper.findById(userid);
    }

    @Override
    public int addNewRecording(DailyReportVo info) {
        Calendar calendar= Calendar.getInstance();
        boolean istouch = info.isIstouch();
        float temperature = Float.parseFloat(info.getTemperature());
        String address = info.getAddress();
        ClientUser clientUser = clientUserMapper.findById(info.getUserid());
        boolean isAbnormal = false;
        // 异常接触分析
        if (istouch){
            clientUser.setIstouch(1);
            isAbnormal = true;
        }
        // 异常温度分析
        if (temperature>38.0){
            clientUser.setIstemperature(1);
            isAbnormal = true;
        }
        // TODO 地区分析未完成
        if ("北京".equals(address)){
            clientUser.setIsarea(1);
            isAbnormal = true;
        }
        if (isAbnormal){
            // 更新用户异常信息
            clientUserMapper.updateClientUserStatus(clientUser.getUserid(),clientUser.getIstouch(),
                    clientUser.getIsarea(),clientUser.getIstemperature());
            // 存入异常人员表
            AbnormalPerson abnormalPerson = new AbnormalPerson(0,clientUser.getUserid(),clientUser.getMobile(),
                    info.getAddress(),info.getAddress(),0,"",dateFormat.format(calendar.getTime()));
            abnormalPersonMapper.insert(abnormalPerson);
        }

        DailyReport dailyReport = new DailyReport(0,info.getUserid(),info.getAddress(),info.getTemperature(),
                dateFormat.format(calendar.getTime()),info.getRemark());
        return dailyReportMapper.insert(dailyReport);
    }

    @Override
    public List<DailyReport> getAllRecording() {
        return dailyReportMapper.findAlldailyreport();
    }
}
