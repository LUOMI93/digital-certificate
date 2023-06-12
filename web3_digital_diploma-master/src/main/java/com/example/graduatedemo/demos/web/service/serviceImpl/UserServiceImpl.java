package com.example.graduatedemo.demos.web.service.serviceImpl;


import com.example.graduatedemo.demos.web.domain.User;
import com.example.graduatedemo.demos.web.repository.UserDao;
import com.example.graduatedemo.demos.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    //@Autowired
    @Resource
    private UserDao userDao;
    //因为要用到UserDao中的方法，所以先通过@Resource注解帮助我们实例化UserDao对象
    //登录业务逻辑代码
    /*@Override
    public User loginService(Long userid, String password) {
        // 如果账号密码都对则返回登录的用户对象，若有一个错误则返回null
        User user = userDao.findByUseridAndPassword(userid, password);
        return user;
    }*/

    @Override
    public User getInfoService(String userid){
        User user =userDao.findByUserid(userid);
        return user;
    }
    @Override
    public Boolean updatePicBase64(User user){
        userDao.updateBase64ByUserid(user.getUserid(),user.getBase64());
        //User u =userDao.findByUserid(user.getUserid());
        //if(u.getPicbase64()!=null){
            return true;
        //}
        //else return false;
    }
    @Override
    public Boolean insertUser(User user){
        userDao.insertUser(user.getUserid(),user.getStyleid());
        return true;
    }
    public Boolean updateUserByUserid(User user){
        userDao.updateUserByUserid(
                user.getUserid(),
                user.getBase64(),
                user.getEvId(),
                user.getTxId(),
                user.getBlockHeight(),
                user.getTxTime());
        return true;
    }

    @Override
    public Boolean updateStyleId(User user){
        userDao.updateStyleIdByUserid(user.getUserid(),user.getStyleid());
        return true;
    }
}
