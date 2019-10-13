package com.itheima.mapper;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //根据ID查询数据
    User selectOneById(Integer id);
    //模糊查询所有数据
    List<User> selectAllByName(String name);
    //添加数据
    void insertUser(User user);
    //修改数据
    void updataUser(User user);
    //删除数据
    void deleteById(Integer id);
    //添加数据,返回自增长ID
    void insertUserById(User user);
    //传递包装类型
    User selectUserByQueryVo(QueryVo queryVo);
    //传递map类型，根据ID查询
    User selectUserByMapId(Map<String,Integer> map);
    //返回map类型
    Map<Integer,String[]> selectMapById(Integer id);
    //传递map类型，根据username模糊查询
    List<User> selectUserByMapName(Map<String,String> map);
    //传递list类型，根据多个id查询数据
    List<User> selectUserByList(List<Integer> list);
    //传递queryVo里面的list类型，根据多个id查询数据
    List<User> selectUserByQueryVoList(QueryVo queryVo);
}
