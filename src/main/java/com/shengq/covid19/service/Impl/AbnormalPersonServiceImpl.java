package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.AbnormalPerson;
import com.shengq.covid19.mapper.AbnormalPersonMapper;
import com.shengq.covid19.service.AbnormalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbnormalPersonServiceImpl implements AbnormalPersonService {
    @Autowired
    AbnormalPersonMapper abnormalPersonMapper;

    /***
     * 根据userid查询异常人员信息
     * @param userid
     * @return
     */
    @Override
    public AbnormalPerson searchById(String userid) {
        return abnormalPersonMapper.findById(userid);
    }

    /***
     * 根据手机号查询异常人员信息
     * @param mobile
     * @return
     */
    @Override
    public AbnormalPerson searchByMobile(String mobile) {
        return abnormalPersonMapper.findByMobile(mobile);
    }

    /***
     * 更新异常人员记录状态
     * @param
     * @param status
     * @return
     */
    @Override
    public int updateStatus(String id, String status,String reamrk) {
        return abnormalPersonMapper.updateStatus(id,status,reamrk);
    }

    /***
     * 返回所有异常人员信息
     * @return
     */
    @Override
    public List<AbnormalPerson> getAllAbnormalPeople() {
        return abnormalPersonMapper.findAllAbnormalPerson();
    }

    /***
     * 返回所有符合status条件的异常人员信息
     * @param status
     * @return
     */
    @Override
    public List<AbnormalPerson> getAllByStatus(String status) {
        return abnormalPersonMapper.findAllAbnormalPersonByStatus(status);
    }
}
