package com.shengq.covid19.service;


import com.shengq.covid19.dao.ConfigImg;

import java.util.List;

public interface ConfigImgService {
    int addImage(String imageName,String imageUrl,Integer type);
    int delImage(Integer id);
    ConfigImg getImageById(Integer id);
    ConfigImg getImgByimgName(String imgName);
    List<ConfigImg> getAllImages();
    List<ConfigImg> getAllImgByType(Integer type);
}

