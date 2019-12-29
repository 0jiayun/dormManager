package pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 学生宿舍安排表,作用表
 */
public class StuDorm {
    @Excel(name = "栋-房号", orderNum = "0")
    private String dNo;

    @Excel(name = "学号", orderNum = "0")
    private String sNo;

    @Excel(name = "姓名", orderNum = "0")
    private String sName;

    @Excel(name = "学 院及专业", orderNum = "0")
    private String dept;

    @Excel(name = "性别", orderNum = "0")
    private String sex;

    @Excel(name = "床位", orderNum = "0")
    private Integer bedNo;

    public Integer getBedNo() {
        return bedNo;
    }

    public void setBedNo(Integer bedNo) {
        this.bedNo = bedNo;
    }

    public String getdNo() {
        return dNo;
    }

    public void setdNo(String dNo) {
        this.dNo = dNo;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
