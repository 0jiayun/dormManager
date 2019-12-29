package pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Students {
    @Excel(name = "学号", orderNum = "0")
    private String sNo;

    @Excel(name = "姓名", orderNum = "0")
    private String sName;

    @Excel(name = "学 院及专业", orderNum = "0")
    private String dept;

    @Excel(name = "性别", orderNum = "0")
    private String sex;

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo == null ? null : sNo.trim();
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
}