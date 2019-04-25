package cn.lxshop.user.dao;

import cn.lxshop.user.model.LXUser;
import cn.lxshop.user.model.MsgModel;

public interface UserDao {
    public abstract MsgModel register(LXUser user);
    public abstract boolean addUser(LXUser user);
    public abstract LXUser findUser(LXUser user);
}
