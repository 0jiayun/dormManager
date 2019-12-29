package pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Dorm {
    @Excel(name = "栋-房号", orderNum = "0")
    private String dNo;

    public String getdNo() {
        return dNo;
    }

    public void setdNo(String dNo) {
        this.dNo = dNo == null ? null : dNo.trim();
    }
}