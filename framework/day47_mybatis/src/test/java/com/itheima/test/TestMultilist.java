package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.domain.AccountCustom;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.mapper.AccountMapper;
import com.itheima.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMultilist {

    private SqlSession session;
    /**
     * 在测试之前执行
     * @throws IOException
     */
    @Before
    public void begin() throws IOException {
        //读取配置文件
        InputStream is= Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //获得会话
        SqlSessionFactory build = builder.build(is);
        //设置自动提交事务
        session = build.openSession(true);
    }

    /**
     * 在测试之后执行
     */
    @After
    public void end(){
        //释放资源
        session.close();
    }

    /**
     * 一对一 查询多条语句，返回accountCustom对象list集合
     */
    @Test
    public void testSelectOneToOne(){
        //通过代理创建dao层对象
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        //调用多表查询一对一的方法
        List<AccountCustom> accountCustoms = mapper.selectOneToOne();
        for (AccountCustom accountCustom : accountCustoms) {
            System.out.println(accountCustom);
        }
    }

    /**
     * 一对一，查询多条语句，运用resultMap
     */
    @Test
    public void testSelectOneToOneByUser(){
        //通过代理创建AccountMapper对象
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        //调用一对一，返回acount对象的集合
        List<Account> accounts = mapper.selectOneToOneByUser();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * 一对多，一个用户有多个账户
     */
    @Test
    public void testSelectOneToMu(){
        //通过代理创建dao层对象
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        //调用一对多的方法
        List<User> users = mapper.selectOneToMu();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectMuToMu(){
        //通过代理创建dao层对象
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        //调用多对多的方法
        List<User> users = mapper.selectMuToMu(2);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 多对多，一个角色有多个用户
     */
    @Test
    public void testSelectMuToMuRole(){
        //通过代理创建dao层对象
        AccountMapper mapper = session.getMapper(AccountMapper.class);
        //调用查询多张表的信息
        List<Role> roles = mapper.selectMuToMuRole(3);
        for (Role role : roles) {
            System.out.println(role);
        }
    }

}
