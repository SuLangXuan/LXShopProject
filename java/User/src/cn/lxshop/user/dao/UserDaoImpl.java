package cn.lxshop.user.dao;

import cn.lxshop.user.model.LXUser;
import cn.lxshop.user.model.MsgModel;
import cn.lxshop.user.utils.LXJDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoImpl implements UserDao  {

    static JdbcTemplate tmp = new JdbcTemplate(LXJDBCUtils.getDataSource());

        public MsgModel register(LXUser user){
        //先去查找有没有对应的用户
        LXUser user1 = findUser(user);
        if (user1!=null){
            System.out.println(user.getUsername()+" "+user.getMobliephonenumber() +"已经存在");
            if (user1.getUsername().equals(user.getUsername())){
                return new MsgModel(0,"用户名已存在，请更换用户名");
            }else if (user1.getMobliephonenumber().equals(user.getMobliephonenumber())){
                return new MsgModel(0,"手机号已存在，请更换手机号");
            }else{
                return new MsgModel(0,"用户已存在");
            }
        }else {
            //没有 添加到数据库中
            boolean b = addUser(user);
            if (b){//注册成功
                return new MsgModel(1,"注册成功");
            }else {
                return new MsgModel(0,"注册失败，数据库错误");
            }
        }

    }


    public  boolean addUser(LXUser user){
        String sql ="INSERT INTO  tb_user (username,mobliephonenumber,userpassword) VALUES (?,?,?)";

        int update = tmp.update(sql, user.getUsername(), user.getMobliephonenumber(), user.getUserpassword());

//        LXUser user1 = tmp.queryForObject(sql, new BeanPropertyRowMapper<LXUser>(LXUser.class), user.getUsername(), user.getMobliephonenumber(), user.getUserpassword());
        return update==1?true:false;
    }

    public  LXUser findUser(LXUser user){
        try{
            String sql = "select * from tb_user where username = ? OR mobliephonenumber = ?";
            LXUser user1 = tmp.queryForObject(sql, new BeanPropertyRowMapper<LXUser>(LXUser.class), user.getUsername(), user.getMobliephonenumber());

            return user1;
        }catch (Exception e){
//           e.printStackTrace();
            return null;
        }

    }

}
