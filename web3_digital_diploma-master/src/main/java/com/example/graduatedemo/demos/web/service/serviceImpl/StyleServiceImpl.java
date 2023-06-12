package com.example.graduatedemo.demos.web.service.serviceImpl;
import com.example.graduatedemo.demos.web.domain.Style;
import com.example.graduatedemo.demos.web.repository.StyleDao;
import com.example.graduatedemo.demos.web.service.StyleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class StyleServiceImpl implements StyleService {
    @Resource
    private StyleDao styleDao;
    @Override
    public String getStyleBase64(Long styleid){
        Style style=styleDao.findByStyleid(styleid);
        String base64=style.getStylebase64();
        return base64;
    }
}
