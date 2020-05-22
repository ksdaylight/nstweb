package edu.ymw.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "from_id")
    private int fromId;
    @Column(name = "target_id")
    private  int  targetId;
    @Column(name = "entity_id")
    private int entityId;
    @Column(name = "entity_type")
    private  int entityType;
    @Column(name = "status")
    private int status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "remark ")
    private  String remark ;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", targetId=" + targetId +
                ", entityId=" + entityId +
                ", entityType=" + entityType +
                ", status=" + status +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
