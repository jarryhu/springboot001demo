package com.example.springboot001demo.mapper;
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
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author My
 * @Date 2020/2/25 9:58
 * @ModifyDate 2020/2/25 9:58
 * @Version 1.0
 */
@Mapper  //mapper注解的好处
@Repository //仓库注解
public interface UserMapper {
    //@Select("select * from user where username=#{username} and password=#{password}")
    public User login(User user);

    @Select("select * from user limit #{pageStart},#{pageEnd}")
    public List<User> getUserList(Map map);

    @Select("select count(*) from user")
    public Integer getUserCount();

    @Select("select * from user where id=#{id}")
    public User getUserByid(Integer id);


    public List<User> getUsersByName(String name);

    //两个参数 一个是按照名字模糊查询 2 按照id的大小


    public List<User> getUsersByWhere(Map map);

    public List<User> getUsersByWhere2(Map map);


    public Integer registerUser(User user);

    public Integer deleteUsers(List<Integer> ids);

    public Integer updateUser(User user);


}
