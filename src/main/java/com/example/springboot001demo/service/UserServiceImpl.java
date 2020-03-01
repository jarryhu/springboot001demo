package com.example.springboot001demo.service;
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

import com.example.springboot001demo.mapper.UserMapper;
import com.example.springboot001demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author My
 * @Date 2020/2/25 12:01
 * @ModifyDate 2020/2/25 12:01
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserSerice {

    @Autowired //自动装配
            UserMapper userMapper;   //在Spring容器中加载mapper实例

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public List<User> getUserList(Map map) {
        return userMapper.getUserList(map);
    }

    @Override
    public User getUserByid(Integer id) {
        return userMapper.getUserByid(id);
    }

    @Override
    public List<User> selectUserByName(String username) {
        return userMapper.getUsersByName(username);
    }

    @Override
    public List<User> getUsersByWhere(Map map) {
        return userMapper.getUsersByWhere(map);
    }

    @Override
    public List<User> getUsersByWhere2(Map map) {
        return userMapper.getUsersByWhere2(map);
    }

    @Override
    public Integer registerUser(User user) {
        return userMapper.registerUser(user);
    }

    @Override
    public Integer getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public Integer deleteUsers(List<Integer> ids) {
        return userMapper.deleteUsers(ids);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

//    @Override
//    public List<User> selectUserByName(String username) {
//        return userMapper.selectUserByName(username);
//    }

}
