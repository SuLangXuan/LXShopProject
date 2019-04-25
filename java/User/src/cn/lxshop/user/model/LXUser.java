package cn.lxshop.user.model;

public class LXUser {

    private int id;
    private String username;
    private String mobliephonenumber;
    private String userpassword;

    public LXUser() {
    }

    public LXUser(int id, String username, String mobliephonenumber, String userpassword) {
        this.id = id;
        this.username = username;
        this.mobliephonenumber = mobliephonenumber;
        this.userpassword = userpassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobliephonenumber() {
        return mobliephonenumber;
    }

    public void setMobliephonenumber(String mobliephonenumber) {
        this.mobliephonenumber = mobliephonenumber;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Override
    public String toString() {
        return "LXUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mobliephonenumber='" + mobliephonenumber + '\'' +
                ", userpassword='" + userpassword + '\'' +
                '}';
    }
}
