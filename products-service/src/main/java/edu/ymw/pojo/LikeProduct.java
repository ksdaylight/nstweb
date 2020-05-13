package edu.ymw.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "likeproduct")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
public class LikeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "user_id")
    private int uId;
    @Column(name = "like_id")
    private  int  likeId;
    @Column(name = "create_time")
    private Date createTime;

    @Override
    public String toString() {
        return "LikeProduct{" +
                "id=" + id +
                ", uId=" + uId +
                ", likeId=" + likeId +
                ", createTime=" + createTime +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public int getuId() {
        return uId;
    }

    public int getLikeId() {
        return likeId;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
