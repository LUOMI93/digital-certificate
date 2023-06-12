package com.example.graduatedemo.demos.web.service;
import com.example.graduatedemo.demos.web.domain.User;
public interface UserService {

    //User loginService(Long userid, String password);
    User getInfoService(String  userid);
    Boolean updatePicBase64(User user);
    Boolean updateStyleId(User user);
    Boolean insertUser(User user);
    Boolean updateUserByUserid(User user);
}
