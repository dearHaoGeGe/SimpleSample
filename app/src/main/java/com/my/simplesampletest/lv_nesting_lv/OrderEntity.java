package com.my.simplesampletest.lv_nesting_lv;

/**
 * 订单的实体类
 *
 * Created by YJH on 2016/9/28.
 */
public class OrderEntity {

    private String name;      //产品名字
    private String category;  //产品的种类 单品、本品、赠品
    private float price;      //产品的单价
    private String TJBM;      //特价编码
    private float reducePrice;//折后价格
    private int num;          //数量
    private float total;      //合计价格
    private String TCBM;      //套餐编码

    public OrderEntity(String name, String category, float price, String TJBM, float reducePrice, int num, float total, String TCBM) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.TJBM = TJBM;
        this.reducePrice = reducePrice;
        this.num = num;
        this.total = total;
        this.TCBM = TCBM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTJBM() {
        return TJBM;
    }

    public void setTJBM(String TJBM) {
        this.TJBM = TJBM;
    }

    public float getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(float reducePrice) {
        this.reducePrice = reducePrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getTCBM() {
        return TCBM;
    }

    public void setTCBM(String TCBM) {
        this.TCBM = TCBM;
    }
}
