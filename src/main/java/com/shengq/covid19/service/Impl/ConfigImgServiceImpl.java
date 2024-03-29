package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.ConfigImg;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.mapper.ConfigImgMapper;
import com.shengq.covid19.service.ConfigImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConfigImgServiceImpl implements ConfigImgService {
    @Autowired
    ConfigImgMapper configImgMapper;
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int addImage(String imageName, String imageUrl,Integer type) {
        SystemUserLoginDetail userDetails = (SystemUserLoginDetail) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return         configImgMapper.addSysConfig(imageName,imageUrl,userDetails.getId(),type);
    }

    @Override
    public int delImage(Integer id) {
        return configImgMapper.delImg(id);
    }

    @Override
    public ConfigImg getImageById(Integer id) {
        return configImgMapper.findImgById(id);
    }

    @Override
    public ConfigImg getImgByimgName(String imgName) {
        return configImgMapper.findImgByimgName(imgName);
    }

    @Override
    public List<ConfigImg> getAllImages() {
        return configImgMapper.findAllImg();
    }

    @Override
    public List<ConfigImg> getAllImgByType(Integer type) {
        return configImgMapper.findAllImgByType(type);
    }
}
