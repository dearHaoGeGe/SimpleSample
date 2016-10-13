package com.my.simplesampletest.lv_diff_type;

/**
 * 不同的type放在同一个实体类中
 *
 * Created by YJH on 2016/10/10.
 */
public class DiffTypeBean {
    private int type;
    private TypeOne     type_1;
    private TypeTwo     type_2;
    private TypeThree   type_3;

    public DiffTypeBean(int type, TypeOne type_1, TypeTwo type_2, TypeThree type_3) {
        this.type = type;
        this.type_1 = type_1;
        this.type_2 = type_2;
        this.type_3 = type_3;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TypeOne getType_1() {
        return type_1;
    }

    public void setType_1(TypeOne type_1) {
        this.type_1 = type_1;
    }

    public TypeTwo getType_2() {
        return type_2;
    }

    public void setType_2(TypeTwo type_2) {
        this.type_2 = type_2;
    }

    public TypeThree getType_3() {
        return type_3;
    }

    public void setType_3(TypeThree type_3) {
        this.type_3 = type_3;
    }
}
