package com.feivirus.orderpay.domain;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_goods
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Goods implements Serializable {
    /**
     * Database Column Remarks:
     *   订单id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   商品编号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_no
     *
     * @mbg.generated
     */
    private String goodsNo;

    /**
     * Database Column Remarks:
     *   商品名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_name
     *
     * @mbg.generated
     */
    private String goodsName;

    /**
     * Database Column Remarks:
     *   库存数量
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods.goods_count
     *
     * @mbg.generated
     */
    private Integer goodsCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_goods
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods.id
     *
     * @return the value of t_goods.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods.id
     *
     * @param id the value for t_goods.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods.goods_no
     *
     * @return the value of t_goods.goods_no
     *
     * @mbg.generated
     */
    public String getGoodsNo() {
        return goodsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods.goods_no
     *
     * @param goodsNo the value for t_goods.goods_no
     *
     * @mbg.generated
     */
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods.goods_name
     *
     * @return the value of t_goods.goods_name
     *
     * @mbg.generated
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods.goods_name
     *
     * @param goodsName the value for t_goods.goods_name
     *
     * @mbg.generated
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods.goods_count
     *
     * @return the value of t_goods.goods_count
     *
     * @mbg.generated
     */
    public Integer getGoodsCount() {
        return goodsCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods.goods_count
     *
     * @param goodsCount the value for t_goods.goods_count
     *
     * @mbg.generated
     */
    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_goods
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
        sb.append(", goodsNo=").append(goodsNo);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsCount=").append(goodsCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}