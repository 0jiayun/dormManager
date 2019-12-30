package service.impl;

import com.google.common.collect.Lists;
import dao.DormDao;
import dao.DormStuDao;
import dao.StudentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Dorm;
import pojo.DormStu;
import pojo.StuDorm;
import pojo.Students;
import service.DormService;
import service.DormStuService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Service
public class DormStuServiceImpl implements DormStuService {
    @Autowired
    private StudentsDao studentsDao;
    @Autowired
    private DormDao dormDao;
    @Autowired
    private DormStuDao dormStuDao;



    @Override
    public Map insertDormStu() {
        Map resultMap=new HashMap();

        Map manMap=new HashMap();
        Map womanMap=new HashMap();

        String man="男";
        String woman="女";
        double dormManNum=3;
        List<Dorm> manDormList=dormDao.getManDorms("13-");
        List<Dorm> womanDormList=dormDao.getWomanDorms("13-");

        List<Students> manList=studentsDao.getStudentsBySex(man);
        List<Students> womanList=studentsDao.getStudentsBySex(woman);
        manMap=arrangeDorm2(manList,manDormList,man);
        womanMap=arrangeDorm2(womanList,womanDormList,woman);
        if (Integer.parseInt(manMap.get("code").toString())==0){
           return womanMap;
        }else {
            return manMap;
        }

    }

    @Override
    public Map getArrange(Map<String, Object> map) {
        Map resultMap=new HashMap();
        String sNo=map.get("sNo").toString();
        String sName=map.get("sName").toString();
        if (sName.equals("")){
            sName=null;
        }
        if (sNo.equals("")){
            sNo=null;
        }
        String dNo=map.get("dNo").toString();

        if (dNo.equals("")){
            dNo=null;
        }
        Integer start=Integer.parseInt(map.get("start").toString());
        Integer number=Integer.parseInt(map.get("number").toString());
        List<StuDorm> list=dormStuDao.getArrange(sNo,sName,dNo,start,number);
        int count=dormStuDao.count(sNo,sName,dNo);
        resultMap.put("msg","SUCCESS");
        resultMap.put("code",0);
        resultMap.put("count",count);
        resultMap.put("data",list);
        return resultMap;

    }

    @Override
    public List<StuDorm> getStuDorms() {
        return dormStuDao.getAllArranges();
    }

    @Override
    public Map deleteAll() {
        Map resultMap=new HashMap();
        try {
            dormStuDao.deleteAllDS();
            dormDao.deleteAllDorm();
            studentsDao.deleteAllStu();
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("msg","数据清理失败");
            resultMap.put("code",1);
            return resultMap;

        }
        resultMap.put("msg","数据全部清理成功");
        resultMap.put("code",0);
        return resultMap;
    }

    public Map arrangeDorm2(List<Students> personList,List<Dorm> dormList,String sex){
        Map resultMap=new HashMap();
        int personNum=personList.size();//总人数
        int dormNum=dormList.size();
        int pid=0;//记录安排到第几个人
        int did=0;//记录安排到第几个宿舍
        while(pid<personNum){
            for (int i=1;i<=3;i++){

                DormStu dormStu=new DormStu(dormList.get(did).getdNo(),personList.get(pid).getsNo(),i);
                try {
                    dormStuDao.insert(dormStu);
                }catch (Exception e){
                    e.printStackTrace();
                    resultMap.put("msg",sex+"宿舍安排出错");
                    resultMap.put("code",1);
                    return resultMap;
                }
                pid++;
                if (pid>=personNum) break;
            }
            did++;
            if (pid>=personNum||did>=dormNum) break;

        }


        resultMap.put("msg","宿舍安排成功");
        resultMap.put("code",0);
        return resultMap;
    }

//    public Map arrangeDorm(String sex,List<Dorm> dormList,Map resultMap,double dormManNum){
//        int dormId=1;//记录使用到哪一个宿舍
//        Map<String,Object> overDorm=new HashMap<>();//记录空余宿舍新信息
//        overDorm.put("空余宿舍",0);//位置dormId
//        overDorm.put("空余床位数量",0);
//        overDorm.put("空余床位起始位置",0);
//        overDorm.put("宿舍号","");
//
//        List<Map<String,String>> deptlist=studentsDao.getDepts(sex);//获取性别专业数
//
//        for (Map<String,String> deptMap:deptlist){
//            List<Students> studentsList=studentsDao.getStuByDeptAndSex(deptMap.get("dept"),sex);//获取专业对应性别人数
//            int deptSentry=1;//专业人数统计哨兵，统计安排到第几个人
//            if (Integer.parseInt(overDorm.get("空余宿舍").toString())!=0){//上一次分配中有剩余床位
//                int bedNum=Integer.parseInt(overDorm.get("空余床位数量").toString());
//                int bed=Integer.parseInt(overDorm.get("空余床位起始位置").toString());
//                Iterator<Students> iterator=studentsList.iterator();
//                int j=0;
//                DormStu dormStu=new DormStu();
//                while (iterator.hasNext()&&j<bedNum){
//                    Students student=iterator.next();
//                    dormStu.setBedNo(bed);
//                    dormStu.setsNo(student.getsNo());
//                    dormStu.setdNo(overDorm.get("宿舍号").toString());
//                    bed++;
//                    iterator.remove();
//                    try {
//                        dormStuDao.insert(dormStu);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                        resultMap.put("msg",sex+"宿舍安排出错");
//                        resultMap.put("code",1);
//                        return resultMap;
//                    }
//                   j++;
//
//                }
//                studentsList= Lists.newArrayList(iterator);
//                dormId++;
//
//            }
//            int studentsNum=studentsList.size();
//            int dormNum=(int)Math.ceil((double)studentsNum/dormManNum);//向上取整
//            int beginNum=dormId;
//            for(;dormId<=(beginNum+dormNum-1);dormId++){
//                int i=1;//床位数
//                for (;i<=dormManNum&&deptSentry<=studentsList.size();i++){
//                    DormStu dormStu=new DormStu(dormList.get(dormId-1).getdNo(),studentsList.get(deptSentry-1).getsNo(),i);
//                    try {
//                        dormStuDao.insert(dormStu);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                        resultMap.put("msg",sex+"宿舍安排出错");
//                        resultMap.put("code",1);
//                        return resultMap;
//                    }
//                    deptSentry++;
//
//                }
//
//            }
//            dormId--;//减去最后一次加的一
//            int overBed=(int)(dormNum*dormManNum-studentsNum);//空余床位
//            if (overBed!=0){//有剩余
//                int bedid=(int)(dormManNum-overBed+1);//空余床位起始序号
//                overDorm.put("空余床位起始位置",bedid);
//                overDorm.put("宿舍号",dormList.get(dormId-1).getdNo());
//                overDorm.put("空余床位数量",overBed);
//                overDorm.put("空余宿舍",dormId);
//            }else {
//                overDorm.put("空余宿舍",0);//位置dormId
//                overDorm.put("空余床位数量",0);
//                overDorm.put("空余床位起始位置",0);
//                overDorm.put("宿舍号","");
//            }
//        }
//
//        resultMap.put("msg","宿舍安排成功");
//        resultMap.put("code",0);
//        return resultMap;
//    }


}
