package com.example.graduatedemo.demos.web.controller;

import com.example.graduatedemo.demos.web.domain.User;
import com.example.graduatedemo.demos.web.service.UserService;
import com.example.graduatedemo.demos.web.utils.JwtUtils;
import com.example.graduatedemo.demos.web.utils.Result;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

@Slf4j
@RestController
//@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    /*@PostMapping("/login")
    //@RequestParam String username, @RequestParam String password
    public Result login(@RequestBody User user){//返回200
        //id=user.getUserid();
        log.info("userid{},password{}",user.getUserid(), user.getPassword());
        User u = userService.loginService(user.getUserid(), user.getPassword());
        if(u!=null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getUserid()); //
            String jwt = JwtUtils.generateJwt(claims); // jwt包含了当前登录的员工信息
            System.out.println(jwt);
            return Result.success(jwt);
        }else{
            return Result.error("123","账号或密码错误！");
        }
    }*/
    @GetMapping("/info/{id}")
    //@PathVariable Long id
    public Result getInfo(@PathVariable String id){
        User u=userService.getInfoService(id);
        if (u != null) {
            log.info("evId:{},",u.getEvId());
            return Result.success(u);
        }
        else {
            return Result.error("400","无效id");
        }
    }
    @PostMapping("/insert")
    public Result insert(@RequestBody User user){//返回200
        //id=user.getUserid();
        log.info("userid{},styleid{}",user.getUserid(),user.getStyleid());
        User u = userService.getInfoService(user.getUserid());
        if(u==null){//数据库中不存在该学号则增加
            userService.insertUser(user);
            return Result.success(user.getStyleid(),"已保存用户名及模版ID");
        }else{//数据库已存在该学号
            return Result.error(user.getStyleid(),"该学号已存在，模版ID已存在");
        }
    }
    @GetMapping("/base64/{id}")
    public Result getPicBase64(@PathVariable String  id){
        User u=userService.getInfoService(id);
        if (u != null) {
            return Result.success("",u.getBase64());
        }
        else {
            return Result.error("1","无效id");
        }
    }
    /*@ResponseBody
    @PutMapping("/uploadpic")
    public Result uploadPicBase64(@RequestBody User user) {
        log.info("userid{},picbase64{}", user.getUserid(), user.getBase64());
        User u=userService.getInfoService((user.getUserid()));
        if (u.getBase64() == null) {
            userService.updatePicBase64(user);
            return Result.success("", "Base64保存成功");
        } else {
            return Result.error("000", "已存证");
        }
    }*/

    @ResponseBody
    @PutMapping("/uploadpic")
    public Result uploadPicBase64(@RequestBody User user) {
        log.info("userid{},picbase64{}", user.getUserid(), user.getBase64());
        User u=userService.getInfoService((user.getUserid()));
        log.info("userid{},picbase64{}", user.getUserid(), user.getBase64());
        if (u.getBase64() == null) {//先判断用户已有base64
            log.info("userid{},picbase64{}", user.getUserid(), user.getBase64());
            // 发送HTTP POST请求
            String url = "http://903.sjd.wiki:3000/api/onchain";
            String jsonBody = "{\"data\": \"" + user.getBase64() + "\"}";
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // 设置请求方法为POST
                con.setRequestMethod("POST");

                // 设置请求头信息
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");

                // 向服务器发送POST请求
                con.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(jsonBody);
                wr.flush();

                // 读取服务器响应
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 输出响应结果
                log.info("onchain response: {}", response.toString());
                User tmp=user;//已有userid和base64，大概
                //
                // 解析JSON数据并存储在数据库中
                /*String json = response.toString();
                ObjectMapper objectMapper = new ObjectMapper();
                User user1 = objectMapper.readValue(json, User.class);*/
                // 假设返回的JSON字符串为resultJson
                ObjectMapper objectMapper = new ObjectMapper();
                String resultJson=response.toString();
                JsonNode jsonNode = objectMapper.readTree(resultJson);
                JsonNode dataNode = jsonNode.get("data");
                String evId = dataNode.get("evId").asText();
                tmp.setEvId(evId);
                String txId = dataNode.get("txId").asText();
                tmp.setTxId(txId);
                Long blockHeight = dataNode.get("blockHeight").asLong();
                tmp.setBlockHeight(blockHeight);
                String txTime = dataNode.get("txTime").asText();
                tmp.setTxTime(txTime);
                //userService.updateChainbyUser
                userService.updateUserByUserid(tmp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result.success( "", "Base64保存成功");
        } else {
            return Result.error("1", "存在存证，未能保存");
        }
    }
    /*@ResponseBody
    @PutMapping("/uploadstyle")
    public Result uploadStyle(@RequestBody User user) {
        log.info("userid{},styleid{}", user.getUserid(), user.getStyleid());
        User u=userService.getInfoService((user.getUserid()));
        if (u.getStyleid() == null) {
            userService.updateStyleId(user);
            return Result.success("", "StyleId保存成功");
        } else {
            return Result.error("000", "已存证");
        }
    }*/

}
