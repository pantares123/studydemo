package com.zch.graduationdesign.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zch.graduationdesign.entity.User;
import com.zch.graduationdesign.mapper.UserMapper;
import com.zch.graduationdesign.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @GetMapping()
    public List<User> index(){
        List<User> all=userMapper.selectList(null);
        return all;
    }

    @PostMapping("/register")
    public Object insertUser(@RequestBody User user){
        System.out.println(user);
        Map<String, Object> columnMap = new HashMap<>();
        Map<String, Object> Map = new HashMap<>();
        columnMap.put("username", user.getUsername());
        List<User> users =userMapper.selectByMap(columnMap);
        if(users.isEmpty()){
            userMapper.insert(user);
            Map.put("code",200);
            Map.put("msg","注册成功");
        }
        else  {
            Map.put("msg","注册成功");
            Map.put("code",100);

        }
        return Map;
    }
    @PostMapping("/changeimg")
    public String changeimg(@RequestBody User user){
        System.out.println(user);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();

        wrapper.eq("username",user.getUsername()).set("img",user.getImg());

        userMapper.update(null,wrapper);
        return "修改成功";
    }

    @PostMapping("/changepwd")
    public Object changepwd(@RequestBody User user){
        System.out.println(user);
        Map<String, Object> map = new HashMap<>();
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",user.getPassword());
        User result=userMapper.selectOne(queryWrapper);
        if(result!=null){
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.eq("username",user.getUsername()).eq("password",user.getPassword()).set("password",user.getEmail());
            userMapper.update(null,wrapper);
            map.put("code",200);
            map.put("msg", "修改成功");
        }else{
            map.put("code",100);
            map.put("msg", "修改失败，请检查原密码是否一致");
        }
     return  map;
    }
    @PostMapping("login")
    public Object userLogin(@RequestBody User user){
             System.out.println(user);
             QueryWrapper queryWrapper=new QueryWrapper();
             queryWrapper.eq("username",user.getUsername());
             queryWrapper.eq("password",user.getPassword());
             User result=userMapper.selectOne(queryWrapper);
             Map<String, Object> map = new HashMap<>();
            if(result!=null){
                String token=getoken(result);
                map.put("questionnum",result.getQuestionnum());
                map.put("token",token);
                map.put("username",result.getUsername());
                map.put("img",result.getImg());
                map.put("msg", "登录成功");
                map.put("code", 200);
            }else{
                map.put("msg", "用户名或者密码错误");
                map.put("code", 100);
            }
        return map;

    }
    public static String  getoken(User user) {
        //Jwts.builder()生成
        //Jwts.parser()验证
        JwtBuilder jwtBuilder =  Jwts.builder()
                .setId(user.getId()+"")
                .setSubject(user.getUsername())    //用户名
                .setIssuedAt(new Date())//登录时间
                .signWith(SignatureAlgorithm.HS256, "my-123").setExpiration(new Date(new
                        Date().getTime()+86400000));
        //设置过期时间
        //前三个为载荷playload 最后一个为头部 header
        System.out.println(jwtBuilder.compact());
        return  jwtBuilder.compact();
    }
    public static void tokenToOut(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("my-123")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("用户id:"+claims.getId());
        System.out.println("用户名:"+claims.getSubject());
        System.out.println("用户时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(claims.getIssuedAt()));System.out.println("过期时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                format(claims.getExpiration()));
        System.out.println("用户角色:"+claims.get("role"));
    }



}
