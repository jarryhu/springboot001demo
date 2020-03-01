package com.example.springboot001demo.controller;
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springboot001demo.pojo.User;
import com.example.springboot001demo.pojo.UserSearchEntity;
import com.example.springboot001demo.service.UserSerice;
import com.example.springboot001demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 用户操作的逻辑
 * @Author My
 * @Date 2020/2/26 14:45
 * @ModifyDate 2020/2/28 16:13 添加用户注册逻辑
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired //自动装配userservice 对象
            UserSerice userSerice;

    /**
     * 得到所有的用户列表
     *
     * @param page 当前页
     * @param rows 每页的记录数
     * @return
     */
    @GetMapping("getUserlist")  //请求的映射
    public Map<String, Object> getUserList(int page, int rows, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, Object> remap = new HashMap<>();
        if (token != null || !token.equals("")) {
            token = token.substring(7);
            boolean verity = JwtUtil.verity(token);
            if (verity) {
                int total = getUserCount();
                int pageStart = (page - 1) * rows;
                Map map = new HashMap();
                map.put("pageStart", pageStart);
                map.put("pageEnd", rows);
                List<User> userList = userSerice.getUserList(map);
                remap.put("total", total);
                remap.put("rows", userList);
                return remap;
            } else {
                throw new RuntimeException("token is error");
            }
        } else {
            throw new RuntimeException("token is error");
        }

    }


    public Integer getUserCount() {
        return userSerice.getUserCount();
    }


    /**
     * 根据用户id得到用户
     *
     * @param id 用户的id
     * @return User
     */
    @GetMapping("getUserById")
    public User getUserid(Integer id) {
        User userByid = userSerice.getUserByid(id);
        return userByid;
    }

    /**
     * 根据用户名模糊查询得到用户
     *
     * @param name
     * @return List<User>
     */
    @GetMapping("getUserByUsername/{name}")
    public List<User> getUserByUsername(@PathVariable("name") String name) {
        return userSerice.selectUserByName(name);
    }

    /**
     * 根据用户名和ID查询用户
     *
     * @param name
     * @param id
     * @return List<User>
     */
    @PostMapping("getUserByWhere")
    public List<User> getUserByWhere(String name, Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", name);
        map.put("id", id);
        return userSerice.getUsersByWhere(map);
    }

    /**
     * @param userSearchEntity
     * @param request
     * @return
     */
    @PostMapping("getUserByWhere2")
    public Map<String, Object> getUserByWhere2(UserSearchEntity userSearchEntity, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, Object> remap = new HashMap<>();
        if (token != null || !token.equals("")) {
            token = token.substring(7);
            boolean verity = JwtUtil.verity(token);
            if (verity) {
                int total = getUserCount();
                Map<String, Object> map = new HashMap<>();
                map.put("username", userSearchEntity.getUsername());
                int pageStart = (userSearchEntity.getPage() - 1) * userSearchEntity.getRows();
                map.put("pageStart", pageStart);
                map.put("pageEnd", userSearchEntity.getRows());
                List<User> userList = userSerice.getUsersByWhere2(map);
                remap.put("total", total);
                remap.put("rows", userList);
                return remap;
            } else {
                throw new RuntimeException("token is error");
            }
        } else {
            throw new RuntimeException("token is error");
        }

    }

    /**
     * 用户登录
     *
     * @param user
     * @return map
     */
    @PostMapping("login")
    public Map<String, Object> Login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        User loginUser = userSerice.login(user);
        if (loginUser != null) {
            String token = JwtUtil.sign(loginUser);
            map.put("code", 1);
            map.put("msg", "登陆成功");
            map.put("token", token);
            map.put("data", loginUser);
        } else {
            map.put("code", -1);
            map.put("msg", "登陆失败");
            map.put("data", null);
        }
        return map;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return map
     */
    @PostMapping("registerUser")
    public Map<String, Object> registerUser(User user) {
        Map<String, Object> map = new HashMap<>();
        Integer result = userSerice.registerUser(user);
        if (result > 0) {
            map.put("code", 1);
            map.put("msg", "注册成功");
        } else {
            map.put("code", -1);
            map.put("msg", "注册失败");
        }
        map.put("data", result);
        return map;

    }

    @PostMapping("deleteUsers")
    public Integer deleteUsers(String[] idsv, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, Object> remap = new HashMap<>();
        if (token != null || !token.equals("")) {
            token = token.substring(7);
            boolean verity = JwtUtil.verity(token);
            if (verity) {
                List<Integer> ids_l = new ArrayList<>();
                if (idsv != null) {
                    for (String id :
                            idsv) {
                        ids_l.add(Integer.parseInt(id));
                    }
                    return userSerice.deleteUsers(ids_l);
                } else {
                    throw new RuntimeException("参数为空");

                }
            } else {
                throw new RuntimeException("参数为空");
            }
        }
        return -1;
    }


    @PostMapping("updateUser")
    public Integer updateUser(User user) {
        return userSerice.updateUser(user);
    }
}
