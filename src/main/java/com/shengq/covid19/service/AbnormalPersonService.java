package com.shengq.covid19.service;

import com.shengq.covid19.dao.AbnormalPerson;

import java.util.List;

public interface AbnormalPersonService {
    /***
     * 根据userid查询异常人员信息
     * @param userid
     * @return
     */
    AbnormalPerson searchById(Integer userid);

    /***
     * 根据手机号查询异常人员信息
     * @param mobile
     * @return
     */
    AbnormalPerson searchByMobile(String mobile);

    /***
     * 更新异常人员记录状态
     * @param id 记录id
     * @param status
     * @return
     */
    int updateStatus(Integer id,String status,String remark);

    /***
     * 返回所有异常人员信息
     * @return
     */
    List<AbnormalPerson> getAllAbnormalPeople();

    /***
     * 返回所有符合status条件的异常人员信息
     * @param status
     * @return
     */
    List<AbnormalPerson> getAllByStatus(String status);
}
