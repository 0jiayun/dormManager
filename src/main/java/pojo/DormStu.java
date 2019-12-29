package pojo;

public class DormStu {
    private String dNo;

    private String sNo;

    private Integer bedNo;

    public DormStu(String dNo, String sNo, Integer bedNo) {
        this.dNo = dNo;
        this.sNo = sNo;
        this.bedNo = bedNo;
    }

    public DormStu() {
    }

    public String getdNo() {
        return dNo;
    }

    public void setdNo(String dNo) {
        this.dNo = dNo == null ? null : dNo.trim();
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo == null ? null : sNo.trim();
    }

    public Integer getBedNo() {
        return bedNo;
    }

    public void setBedNo(Integer bedNo) {
        this.bedNo = bedNo;
    }
}