package com.example.graduatedemo.demos.web.controller;

import com.example.graduatedemo.demos.web.service.StyleService;
import com.example.graduatedemo.demos.web.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/style")
public class StyleController {
    @Resource
    private StyleService styleService;
    @GetMapping("/getstyle/{styleid}")
    public Result getstylebase64(@PathVariable Long styleid){
        log.info("{}",styleid);
        String base64=styleService.getStyleBase64(styleid);
        if(base64!=null){
            return Result.success(base64,"图片抽取成功");
        }
        else {
            return Result.error("100","无效id");
        }
    }
}
