package edu.ymw.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.ymw.service.ProductService;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "product")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String title;
    private int status;
    private  Date create_time;
    private int comment_count;
    private double score;

    @ManyToOne
    @JoinColumn(name="temp_id")
    private Template template;

    private float scale;
    private int weight;
    private int color;

    @Transient
    private String statusDesc;

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
    public String getStatusDesc(){
        if(null!=statusDesc)
            return statusDesc;
        String desc ="未知";
        switch(status){
            case ProductService.unfinished :
                desc="等待渲染";
                break;
            case ProductService.hide:
                desc="隐藏";
                break;
            case ProductService .open:
                desc="公开";
                break;
            case ProductService .baned:
                desc="封禁";
                break;
            default:
                desc="未知";
        }
        statusDesc = desc;
        return statusDesc;
    }
    public int getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public int getComment_count() {
        return comment_count;
    }

    public double getScore() {
        return score;
    }

    public Template getTemplate() {
        return template;
    }

    public float getScale() {
        return scale;
    }

    public int getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                ", comment_count=" + comment_count +
                ", score=" + score +
                ", template=" + template +
                ", scale=" + scale +
                ", weight=" + weight +
                ", color=" + color +
                '}';
    }
}
