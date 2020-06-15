package com.gdou;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdou.MyUitls.DateTime;
import com.gdou.dao.RecordMapper;
import com.gdou.dao.UserMapper;
import com.gdou.entity.Record;
import com.gdou.entity.User;
import com.gdou.service.RecordService;
import com.gdou.service.RoleService;
import com.gdou.service.UserService;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class Springboot01ApplicationTests {

    @Resource
    private UserMapper userMapper;
//    @Autowired
//    private UserService userService;

    @Resource
    UserService userService;
    @Resource
    RoleService roleService;

    @Test
    public void contextLoads() {
        //userMapper 里有各种方法 (调用的是dao层)


        //插入数据
//        User user = new User();
//        user.setName("周香庭");user.setGender(true);
//        userService.save(user);

        //List<User> userList = userMapper.selectList(null);
        //userList.forEach(System.out::println);
//
//        System.out.println("这里是自定义的方法使用注解");
//        List<User> myUserList = userMapper.findAllUser();
//        myUserList.forEach(System.out::println);
//
//        System.out.println("这里是自定义的方法使用xml");
//        List<User> myUser = userMapper.findUserXml();
//        myUser.forEach(System.out::println);
//
//
//        List<User> userList = userService.list();
//        userList.forEach(System.out::println);
    }


    //分页查询
    @Test
    public void selectPage() {
//        Page<User> page = new Page<>(1,5);
//
//        IPage<User> userIPage = userMapper.selectPage(page,null);
//
//        System.out.println("总页数: "+userIPage.getPages());
//        System.out.println("总记录："+userIPage.getTotal());
//
//        userIPage.getRecords().forEach(System.out::println);

//        System.out.println("test");
//        List<User> users = userService.selectByPage(1, 5);
//        users.forEach(System.out::println);
        System.out.println(userService.getTotal());
    }

    @Test
    public void deleteById() {
        if (userService.removeById(18)) {
            System.out.println("remove suc");
        } else {
            System.out.println("down");
        }
    }

    @Test
    public void roleSave() {
//        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        System.out.println(time);
        //System.out.println(roleService.findProper("admin", "123456"));
    }

    @Resource
    private RecordMapper recordMapper;
    @Resource
    private RecordService recordService;

    @Test
    public void record() {
        //System.out.println(recordMapper.sign("2019-07-11",1));

        // System.out.println(recordMapper.sign("2019-07-11",1).getStatus());

//        Record record=new Record();
//        record.setName("涂超");
//        record.setUid(1);
//        record.setStatus(true);
//
//        recordService.dailySign(record);


        //System.out.println(recordMapper.selectById(1));
    }

    @Test
    public void testsign() {
        //每日签到函数
        List<User> userList = userService.list();
        for (User user : userList) {
            Record record = new Record();
            record.setUid(user.getId());
            record.setName(user.getName());
            recordService.dailySign(record);
        }
        System.out.println("打卡完成");
    }

    @Test
    public void DT(){
        System.out.println(DateTime.getDT("yyyy-MM-dd HH:mm:ss"));
    }
}
