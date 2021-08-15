package com.lyj.demo.elasticsearch;

import java.util.Date;

/**
 * @program: springelasticSearchdemo
 * @Date: 2021/8/15 8:36
 * @Author: 李玉杰
 * @Description:
 */
public class User {

    private String userName;

    private Integer age;

    private String sex;

    private Date birthday;

    public User() {
    }

    public User(String userName, Integer age, String sex, Date birthday) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
