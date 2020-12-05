package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.ConfigImg;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.mapper.ConfigImgMapper;
import com.shengq.covid19.service.ConfigImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConfigImgServiceImpl implements ConfigImgService {
    @Autowired
    ConfigImgMapper configImgMapper;
    @Override
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
    public ConfigImg getImage(Integer id) {
        return configImgMapper.getImg(id);
    }

    @Override
    public List<ConfigImg> getAllImages() {
        return configImgMapper.getAllImg();
    }

    @Override
    public List<ConfigImg> getAllImgByType(Integer type) {
        return configImgMapper.getAllImgByType(type);
    }
}
