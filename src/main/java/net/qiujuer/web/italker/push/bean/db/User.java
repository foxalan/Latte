package net.qiujuer.web.italker.push.bean.db;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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






}
