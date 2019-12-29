package service.impl;

import dao.StudentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Students;
import service.StudentsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service

public class StudentsServiceImpl implements StudentsService {
    @Autowired
    private StudentsDao studentsDao;

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
}
