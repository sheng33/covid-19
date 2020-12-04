package com.shengq.covid19.service.Impl;

import com.shengq.covid19.dao.ClientConfigImg;
import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.mapper.ClientConfigImgMapper;
import com.shengq.covid19.service.ClientConfigImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ClientConfigImgServiceImpl implements ClientConfigImgService {
    @Autowired
    ClientConfigImgMapper configImgMapper;
    @Override
    public int addImage(String imageName, String imageUrl) {
        SystemUserLoginDetail userDetails = (SystemUserLoginDetail) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return         configImgMapper.addSysConfig(imageName,imageUrl,userDetails.getId());
    }

    @Override
    public int delImage(Integer id) {
        return configImgMapper.delImg(id);
    }

    @Override
    public ClientConfigImg getImage(Integer id) {
        return configImgMapper.getImg(id);
    }

    @Override
    public List<ClientConfigImg> getAllImages() {
        return configImgMapper.getAllImg();
    }
}
