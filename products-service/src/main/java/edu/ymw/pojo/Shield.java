package edu.ymw.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "shield")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
public class Shield {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "user_id")
    private int uId;
    @Column(name = "product_id")
    private int pId;

    @Override
    public String toString() {
        return "Shield{" +
                "id=" + id +
                ", uId=" + uId +
                ", pId=" + pId +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getId() {
        return id;
    }

    public int getuId() {
        return uId;
    }

    public int getpId() {
        return pId;
    }
}
