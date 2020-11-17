package com.shengq.covid19.service;


import com.shengq.covid19.dao.DailyReport;
import com.shengq.covid19.vo.DailyReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyReportService {
    /***
     * 查询用户每日登记信息
     * @param userid
     * @return
     */
    List<DailyReport> findInfoById(Integer userid);

    /***
     * 每日信息填报
     * @param info
     * @return
     */
    int addNewRecording(DailyReportVo info);

    /***
     * 返回所有表中填报记录
     * @return
     */
    List<DailyReport> getAllRecording();
}
