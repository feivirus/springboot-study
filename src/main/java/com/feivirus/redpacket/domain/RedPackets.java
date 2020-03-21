package com.feivirus.redpacket.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_red_packets
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class RedPackets implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   发红包的账号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.uid
     *
     * @mbg.generated
     */
    private Integer uid;

    /**
     * Database Column Remarks:
     *   版本
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.version
     *
     * @mbg.generated
     */
    private Integer version;

    /**
     * Database Column Remarks:
     *   红包总额
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.total_amount
     *
     * @mbg.generated
     */
    private Double totalAmount;

    /**
     * Database Column Remarks:
     *   红包总个数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.total_number
     *
     * @mbg.generated
     */
    private Integer totalNumber;

    /**
     * Database Column Remarks:
     *   剩余金额
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.over_amount
     *
     * @mbg.generated
     */
    private Double overAmount;

    /**
     * Database Column Remarks:
     *   剩余个数
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.over_number
     *
     * @mbg.generated
     */
    private Integer overNumber;

    /**
     * Database Column Remarks:
     *   截止时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.over_time
     *
     * @mbg.generated
     */
    private Date overTime;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_red_packets.add_time
     *
     * @mbg.generated
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_red_packets
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.id
     *
     * @return the value of t_red_packets.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.id
     *
     * @param id the value for t_red_packets.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.uid
     *
     * @return the value of t_red_packets.uid
     *
     * @mbg.generated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.uid
     *
     * @param uid the value for t_red_packets.uid
     *
     * @mbg.generated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.version
     *
     * @return the value of t_red_packets.version
     *
     * @mbg.generated
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.version
     *
     * @param version the value for t_red_packets.version
     *
     * @mbg.generated
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.total_amount
     *
     * @return the value of t_red_packets.total_amount
     *
     * @mbg.generated
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.total_amount
     *
     * @param totalAmount the value for t_red_packets.total_amount
     *
     * @mbg.generated
     */
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.total_number
     *
     * @return the value of t_red_packets.total_number
     *
     * @mbg.generated
     */
    public Integer getTotalNumber() {
        return totalNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.total_number
     *
     * @param totalNumber the value for t_red_packets.total_number
     *
     * @mbg.generated
     */
    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.over_amount
     *
     * @return the value of t_red_packets.over_amount
     *
     * @mbg.generated
     */
    public Double getOverAmount() {
        return overAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.over_amount
     *
     * @param overAmount the value for t_red_packets.over_amount
     *
     * @mbg.generated
     */
    public void setOverAmount(Double overAmount) {
        this.overAmount = overAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.over_number
     *
     * @return the value of t_red_packets.over_number
     *
     * @mbg.generated
     */
    public Integer getOverNumber() {
        return overNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.over_number
     *
     * @param overNumber the value for t_red_packets.over_number
     *
     * @mbg.generated
     */
    public void setOverNumber(Integer overNumber) {
        this.overNumber = overNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.over_time
     *
     * @return the value of t_red_packets.over_time
     *
     * @mbg.generated
     */
    public Date getOverTime() {
        return overTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.over_time
     *
     * @param overTime the value for t_red_packets.over_time
     *
     * @mbg.generated
     */
    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_red_packets.add_time
     *
     * @return the value of t_red_packets.add_time
     *
     * @mbg.generated
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_red_packets.add_time
     *
     * @param addTime the value for t_red_packets.add_time
     *
     * @mbg.generated
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_red_packets
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", version=").append(version);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", totalNumber=").append(totalNumber);
        sb.append(", overAmount=").append(overAmount);
        sb.append(", overNumber=").append(overNumber);
        sb.append(", overTime=").append(overTime);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}