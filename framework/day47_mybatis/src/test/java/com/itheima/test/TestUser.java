package com.itheima.test;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.tree.VariableHeightLayoutCache;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TestUser {
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
     * 代理dao的方式 根据ID查询数据，返回user对象
     */
    @Test
    public void testSelectOneById(){
        //代理创建对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //执行方法
        User user = mapper.selectOneById(2);
        System.out.println(user);
    }

    /**
     * 代理dao的方式，根据模糊查询，返回user的list集合
     */
    @Test
    public void testSelectAllByName(){
        //获得代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //执行方法
        List<User> users = mapper.selectAllByName("%小%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 代理dao的方式，添加一条数据
     */
    @Test
    public void testInsertUser(){
        //创建代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //创建user对象
        User user=new User();
        user.setUsername("周杰伦");
        user.setBirthday("2019-07-09");
        user.setSex("男");
        user.setAddress("台湾");
        //执行方法
        mapper.insertUser(user);
    }

    /**
     * 代理dao的方式，修改一条数据
     */
    @Test
    public void testUpdateUser(){
        //创建代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //创建user对象
        User user=new User();
        user.setUsername("宋小宝");
        user.setAddress("湖南");
        user.setId(1);
        //执行修改的方法
        mapper.updataUser(user);
    }

    /**
     * 代理dao的方式，根据ID删除一条数据
     */
    @Test
    public void testDeleteById(){
        //创建代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //执行删除方法
        mapper.deleteById(61);
    }

    /**
     * 代理dao的方式，添加一条数据，返回自增长的ID
     */
    @Test
    public void testInsertUserById(){
        //创建代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //创建user对象
        User user=new User();
        user.setUsername("林俊杰");
        user.setBirthday("2019-07-09");
        user.setSex("男");
        user.setAddress("台湾");
        //执行方法
        mapper.insertUserById(user);

        System.out.println(user.getId());
    }

    /**
     * 代理dao的方式，传递queryVo对象，根据user对象ID的值查询，返回user对象
     */
    @Test
    public void testSelectUserByQueryVo(){
        //创建代理对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //创建queryVo 对象
        QueryVo queryVo=new QueryVo();
        User user=new User();
        user.setId(1);
        queryVo.setUser(user);
        //执行根据queryVo对象查询数据方法
        User user1 = mapper.selectUserByQueryVo(queryVo);
        System.out.println(user1);
    }

    /**
     * 代理dao的方式，传递map类型，根据id查询，返回user对象
     */
    @Test
    public void testSelectUserByMapIdj(){
        //通过代理创建dao对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //创建map
        Map<String,Integer> map =new HashMap<String, Integer>();
        map.put("id",2);
        //执行传递map类型的方法
        User user = mapper.selectUserByMapId(map);
        System.out.println(user);
    }


    /**
     * 代理dao的方式，根据ID查询，返回map类型
     */
    @Test
    public void testSelectMapById(){
        //通过代理创建dao层对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //执行返回map类型的方法
        Map<Integer, String[]> map = mapper.selectMapById(3 );
        System.out.println(map);
    }

    /**
     * 代理dao的方式，传参map类型，根据username模糊查询多个数据
     */
    @Test
    public void testSelectUserByMapName(){
        //通过代理获得dao层对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //创建map对象，存放传递的参数
        Map<String,String> map=new HashMap<String, String>();
        map.put("username","%小%");
        //调用传递map类型，根据username模糊查询
        List<User> users = mapper.selectUserByMapName(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 代理dao的方式，传参list类型，根据多个id查询多条语句
     */
    @Test
    public void testSelectUserByList(){
        //通过代理创建dao层对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //创建list的对象
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        //调用传递list类型的方法
        List<User> list1 = mapper.selectUserByList(list);
        for (User user : list1) {
            System.out.println(user);
        }
    }


    /**
     * 代理dao的方式，传参queryvo里面的list类型，根据多个ID查询多条语句
     */
    @Test
    public void testSelectUserByQueryVoList(){
        //通过代理创建dao层对象
        UserMapper mapper = session.getMapper(UserMapper.class);
        //创建queryvo对象
        QueryVo queryVo=new QueryVo();
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        queryVo.setList(list);
        //调用传递queryvo里的list类型
        List<User> list1 = mapper.selectUserByQueryVoList(queryVo);
        for (User user : list1) {
            System.out.println(user);
        }
    }




}
