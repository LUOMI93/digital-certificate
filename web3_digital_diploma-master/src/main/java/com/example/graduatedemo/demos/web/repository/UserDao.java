package com.example.graduatedemo.demos.web.repository;

import com.example.graduatedemo.demos.web.domain.User;
//import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
//@Mapper
@Repository
// extends JpaRepository<User, Long>
public interface UserDao extends JpaRepository<User,String>{
    //User findByUseridAndPassword(String userid, String password);//通过用户名user_id和密码查找用户
    User findByUserid(String userid);//通过用户名user_id查找用户
   // Boolean updatePicBase64ByUserid(User user);
   @Transactional
   @Modifying
   @Query("UPDATE User u SET u.base64 = :base64 WHERE u.userid = :userid")
   void updateBase64ByUserid(@Param("userid") String userid, @Param("base64") String base64);

    @Modifying
    @Transactional
    @Query(value = "insert into user_table(userid, styleid) VALUES (?1,?2)",nativeQuery = true)
    //@Query("INSERT INTO User(userid, base64) VALUES (:userid, :base64)")
    void insertUser(String userid,String styleid);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.base64 = :base64,u.evId=:evId,u.txId=:txId,u.blockHeight=:blockHeight,u.txTime=:txTime WHERE u.userid = :userid")
    void updateUserByUserid(@Param("userid") String userid,
                            @Param("base64") String base64,
                            @Param("evId") String evId,
                            @Param("txId") String txId,
                            @Param("blockHeight") Long blockHeight,
                            @Param("txTime") String txTime);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.styleid = :styleid WHERE u.userid = :userid")
    void updateStyleIdByUserid(@Param("userid") String userid, @Param("styleid") String styleid);
}
