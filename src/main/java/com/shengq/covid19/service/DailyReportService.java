package com.shengq.covid19.service;


import com.shengq.covid19.dao.DailyReport;
import com.shengq.covid19.vo.DailyReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyReportService {

    DailyReport findInfoById(Integer userid);

    int addNewRecording(DailyReportVo info);

    List<DailyReport> getAllRecording();
}
