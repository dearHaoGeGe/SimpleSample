package com.my.simplesampletest.jsonobjecttostring;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YJH on 2017/1/12 23:27.
 */

public class CasesBean {


    /**
     * bankRead : 1
     * bankResult : 2
     * caseName : 测试案件01
     * caseNo : （2016）湘执测试01号
     * gewRead : 0
     * gewResult : 0
     * id : 201606060001
     * policeRead : 1
     * policeResult : 2
     * registerDate : 2016-06-06 13:59:06
     */

    /**
     * @SerializedName 作用：用于和json数据统一
     * @Expose 作用：实体类转换成json数据时，只转换有@Expose注解的，没有注解的不转换（必须用GsonBuilder这个类转换）
     * <p>
     * http://www.cnblogs.com/newcaoguo/p/6104884.html
     * http://www.cnblogs.com/tittles0k/p/5621362.html
     */
    @SerializedName("id")
    @Expose
    private String caseId;
    private int bankRead;
    private int bankResult;
    @Expose
    private String caseName;
    @Expose
    private String caseNo;
    private int gewRead;
    private int gewResult;
    private int policeRead;
    private int policeResult;
    @Expose
    private String registerDate;

    public int getBankRead() {
        return bankRead;
    }

    public void setBankRead(int bankRead) {
        this.bankRead = bankRead;
    }

    public int getBankResult() {
        return bankResult;
    }

    public void setBankResult(int bankResult) {
        this.bankResult = bankResult;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public int getGewRead() {
        return gewRead;
    }

    public void setGewRead(int gewRead) {
        this.gewRead = gewRead;
    }

    public int getGewResult() {
        return gewResult;
    }

    public void setGewResult(int gewResult) {
        this.gewResult = gewResult;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public int getPoliceRead() {
        return policeRead;
    }

    public void setPoliceRead(int policeRead) {
        this.policeRead = policeRead;
    }

    public int getPoliceResult() {
        return policeResult;
    }

    public void setPoliceResult(int policeResult) {
        this.policeResult = policeResult;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
