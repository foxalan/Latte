package net.qiujuer.web.italker.push.bean.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户表
 */
@Table(name = "tb_user")
@Entity
public class User {

    @Id
    //主键
    @PrimaryKeyJoinColumn
    //一种基本没有重复值的ID
    @GeneratedValue(generator = "uuid")
    //把uuid的生成器定义为uuid2,uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    //不允许更改，不允许为null
    @Column(updatable = false,nullable = false)
    private String id;

    //用户名唯一
    @Column(nullable = false,length = 128,unique = true)
    private String name;

    //电话号唯一
    @Column(nullable = false,length = 62,unique = true)
    private String phone;

    //密码
    @Column(nullable = false)
    private String password;

    //头像
    @Column
    private String portrait;

    //描述
    @Column
    private String description;

    //性别
    @Column(nullable = false)
    private int sex = 0;

    //可以拉取用户信息，并且唯一
    @Column(unique = true)
    private String token;

    //用于推送的设备ID
    @Column
    private String pushId;

    //创建时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    //更新时间
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    //最后一次
    @Column
    private LocalDateTime lastReceivedAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getLastReceivedAt() {
        return lastReceivedAt;
    }

    public void setLastReceivedAt(LocalDateTime lastReceivedAt) {
        this.lastReceivedAt = lastReceivedAt;
    }
}
