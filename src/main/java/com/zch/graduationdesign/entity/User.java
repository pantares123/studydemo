package com.zch.graduationdesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Integer questionnum;
    private String img;

    public String getUsername() {
        return username;
    }



    public String getPassword() {
        return password;
    }


    public String getNickname() {
        return nickname;
    }



    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getQuestionnum() {
        return questionnum;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
