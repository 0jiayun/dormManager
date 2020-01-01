package service.impl;

import dao.DormStuDao;
import dao.StudentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.DormStu;
import pojo.Students;
import service.StudentsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service

public class StudentsServiceImpl implements StudentsService {
    @Autowired
    private StudentsDao studentsDao;

    @Autowired
    private DormStuDao dormStuDao;

    @Override
    public Map insertStudents(List<Students> list) {
        Map resultMap=new HashMap();
        for (Students s:list){
            try {
                studentsDao.insert(s);
            }catch (Exception e){
                e.printStackTrace();
                resultMap.put("msg","学生数据插入失败");
                resultMap.put("code",1);

                return resultMap;
            }
        }
        resultMap.put("msg","学生数据插入成功");
        resultMap.put("code",0);
        return resultMap;

    }

    @Override
    public Map getStudents(Map<String, Object> map) {
        Map resultMap=new HashMap();
        String sNo=map.get("sNo").toString();
        String sName=map.get("sName").toString();
        if (sName.equals("")){
            sName=null;
        }
        if (sNo.equals("")){
            sNo=null;
        }
        Integer start=Integer.parseInt(map.get("start").toString());
        Integer number=Integer.parseInt(map.get("number").toString());
        List<Students> studentsList=studentsDao.getStudents(sNo,sName,start,number);
        int count=studentsDao.count(sNo,sName);
        resultMap.put("msg","SUCCESS");
        resultMap.put("code",0);
        resultMap.put("count",count);
        resultMap.put("data",studentsList);
        return resultMap;
    }

    @Override
    public Map countDeptStudents() {
        Map resultMap=new HashMap();
        List<Map<String,Object>> dataList=new ArrayList<>();
        List<Map<String,Object>> list=studentsDao.getAllDepts();
        for (Map<String,Object> map:list){
            map.put("manNum",studentsDao.countDeptStudents(map.get("dept").toString(),"男"));
            map.put("womanNum",studentsDao.countDeptStudents(map.get("dept").toString(),"女"));
            dataList.add(map);
        }
        resultMap.put("msg","SUCCESS");
        resultMap.put("code",0);
        resultMap.put("data",dataList);

        return resultMap;
    }

    @Override
    public Map studentsLogin(Map<String, Object> map) {
        String userName=map.get("userName").toString();
        String password=map.get("password").toString();
        Map resultMap=new HashMap();
        try {
            Students students=studentsDao.studentsLogin(userName,password);
            resultMap.put("code",0);
            resultMap.put("msg","登陆成功");
            resultMap.put("data",students);

        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code",1);
            resultMap.put("msg","学生登陆出错");
            resultMap.put("data","null");
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public Map updateStudents(Students students) {
        Map resultMap=new HashMap();
        try {
            studentsDao.updateByPrimaryKeySelective(students);
            resultMap.put("code",0);
            resultMap.put("msg","修改成功");
            resultMap.put("data","SUCCESS");

        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code",1);
            resultMap.put("msg","修改出错");
            resultMap.put("data","null");
            return resultMap;
        }
        return resultMap;

    }

    @Override
    public Map deleteStudents(String sNo) {
        Map resultMap=new HashMap();
        try {

            dormStuDao.deleteBysNo(sNo);//先删除中间表记录，有关联
            studentsDao.deleteByPrimaryKey(sNo);
            resultMap.put("code",0);
            resultMap.put("msg","删除成功");
            resultMap.put("data","SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code",1);
            resultMap.put("msg","删除失败");
            resultMap.put("data","null");
        }
        return resultMap;
    }
}
