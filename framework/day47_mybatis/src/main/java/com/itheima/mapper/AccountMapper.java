package com.itheima.mapper;

import com.itheima.domain.Account;
import com.itheima.domain.AccountCustom;
import com.itheima.domain.Role;
import com.itheima.domain.User;

import java.util.List;

public interface AccountMapper {
    //一对一 查询多条语句，返回accountCustom对象list集合
    List<AccountCustom> selectOneToOne();
    //一对一，查询多条语句，返回account对象，里面封装了user对象
    List<Account> selectOneToOneByUser();
    //一对多，
    List<User> selectOneToMu();
    //多对多，一个用户有多个角色
    List<User> selectMuToMu(Integer id);
    //多对多，一个角色有多个用户
    List<Role> selectMuToMuRole(Integer id);
}
