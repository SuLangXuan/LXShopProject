package cn.lxshop.user.model;

public class MsgModel {

    private int msgcode;
    private String msg;

    public MsgModel() {
    }

    public MsgModel(int msgcode, String msg) {
        this.msgcode = msgcode;
        this.msg = msg;
    }



    public int getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(int msgcode) {
        this.msgcode = msgcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MsgModel{" +
                "msgcode=" + msgcode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
