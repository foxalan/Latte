package net.qiujuer.web.italker.push.bean.db;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户关注表
 */

@Table(name = "tb_user_follow")
@Entity
public class UserFollow {

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


    //关注人
    //多对一，你可以关注很多人
    //optional不可以选，必须存储
    @ManyToOne(optional = false)
    @JoinColumn(name = "originId")
    private User origin;

    @Column(nullable = false,updatable = false,insertable = false)
    private String originId;

    //被关注者
    @ManyToOne(optional = false)
    @JoinColumn(name = "targetId")
    private User target;

    @Column(nullable = false,updatable = false,insertable = false)
    private String targetId;

    //备注
    @Column
    private String alias;

    //创建时间
    @CreationTimestamp
    @Column
    private LocalDateTime createAt = LocalDateTime.now();

    //更新时间
    @UpdateTimestamp
    @Column
    private LocalDateTime updateAt = LocalDateTime.now();

    //我关注的人的列表方法
    //对应的数据库的字段为TB_USER_FOLLOW.originId
    @JoinColumn(name = "originId")
    //定义为懒加载，默认加载User信息的时候，并不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<UserFollow> following = new HashSet<>();

    //关注我的人
    @JoinColumn(name = "targetId")
    //定义为懒加载，默认加载User信息的时候，并不查询这个集合
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<UserFollow> follows = new HashSet<>();

    public String getId() {
        return id;
    }

    public Set<UserFollow> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserFollow> following) {
        this.following = following;
    }

    public Set<UserFollow> getFollows() {
        return follows;
    }

    public void setFollows(Set<UserFollow> follows) {
        this.follows = follows;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    //
}
