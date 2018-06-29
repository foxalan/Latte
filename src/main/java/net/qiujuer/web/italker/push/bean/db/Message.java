package net.qiujuer.web.italker.push.bean.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_message")
public class Message {

    @Id
    //主键
    @PrimaryKeyJoinColumn
    //把uuid的生成器定义为uuid2,uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    //不允许更改，不允许为null
    @Column(updatable = false,nullable = false)
    private String id;

    //内容
    private String content;

    private String attach;

    private int type;

    //不可以选择
    @JoinColumn(name = "senderId")
    @ManyToOne(optional = false)
    private User sender;

    @Column(updatable = false,insertable = false)
    private String senderId;


    @ManyToOne(optional = false)
    @JoinColumn(name = "receiverId")
    private User receiver;

    @Column(updatable = false,insertable = false)
    private String receiverId;



    //创建时间
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    //更新时间
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

}
