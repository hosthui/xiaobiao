package com.lyh.xiaobiao.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String qqOpenid;
    private String wxOpenid;
    private String realName;
    private Long age;
    private String phone;
    private String gender;
    private String info;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registerTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loginTime;
    private String pic;
    private Long look;
    private String isSecret;
    private String deptName;
    private Long deptId;
    @Transient
<<<<<<< HEAD
    private Long userFans;

    public Long getUserFans() {
        return userFans;
    }

    public void setUserFans(Long userFans) {
        this.userFans = userFans;
    }

    public Long getId() {
=======
    private String userFocusId;

    
	
	
	public String getUserFocusId() {
		return userFocusId;
	}
	
	public void setUserFocusId(String userFocusId) {
		this.userFocusId = userFocusId;
	}
	
	public Long getId() {
>>>>>>> 18d2331206a5c8aee6ff2e2786ce072cde6d1b67
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getLook() {
        return look;
    }

    public void setLook(Long look) {
        this.look = look;
    }

    public String getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(String isSecret) {
        this.isSecret = isSecret;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
	
	
}
