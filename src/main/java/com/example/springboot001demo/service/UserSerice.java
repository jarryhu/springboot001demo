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

import com.example.springboot001demo.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserSerice
 * @Description TODO
 * @Author My
 * @Date 2020/2/25 11:59
 * @ModifyDate 2020/2/25 11:59
 * @Version 1.0
 */
public interface UserSerice {
    public User login(User user);
    public List<User> getUserList(Map map);
    public User getUserByid(Integer id);
    public List<User> selectUserByName(String username);
    public List<User> getUsersByWhere(Map map);
    public List<User> getUsersByWhere2(Map map);
    public Integer registerUser(User user);
    public Integer getUserCount();
    public Integer deleteUsers(List<Integer> ids);
    public Integer updateUser(User user);
}
