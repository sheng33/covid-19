package com.shengq.covid19.service;


import com.shengq.covid19.dao.ClientConfigImg;

import java.util.List;

public interface ClientConfigImgService {
    int addImage(String imageName,String imageUrl);
    int delImage(Integer id);
    ClientConfigImg getImage(Integer id);
    List<ClientConfigImg> getAllImages();
}
