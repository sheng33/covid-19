package com.shengq.covid19.controller;

import com.shengq.covid19.config.Result;
import com.shengq.covid19.service.AbnormalPersonService;
import com.shengq.covid19.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/abnormalList")
public class AbnormalPersonController {
    final AbnormalPersonService abnormalPersonService;

    public AbnormalPersonController(AbnormalPersonService abnormalPersonService) {
        this.abnormalPersonService = abnormalPersonService;
    }

    @GetMapping("/getAllList")
    public Result<?> getAllList(){
        return ResultUtil.success(abnormalPersonService.getAllAbnormalPeople());
    }

    @PostMapping("/getAllList")
    public Result<?> getAllListByStatus(String status){
        return ResultUtil.success(abnormalPersonService.getAllByStatus(status));
    }


}
