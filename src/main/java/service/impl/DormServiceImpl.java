package service.impl;

import dao.DormDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Dorm;
import service.DormService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DormServiceImpl implements DormService {
    @Autowired
    private DormDao dormDao;

    @Override
    public Map insertDorm(List<Dorm> list) {
        Map resultMap=new HashMap();
        for (Dorm dorm:list){
            try {
                dormDao.insert(dorm);
            }catch (Exception e){
                e.printStackTrace();
                resultMap.put("msg","宿舍信息插入失败");
                resultMap.put("code",1);

                return resultMap;
            }
        }
        resultMap.put("msg","宿舍信息插入成功");
        resultMap.put("code",0);
        return resultMap;
    }

    @Override
    public Map getDorms(Map<String, Object> map) {
        Map resultMap=new HashMap();
        String dNo=map.get("dNo").toString();

        if (dNo.equals("")){
            dNo=null;
        }
        Integer start=Integer.parseInt(map.get("start").toString());
        Integer number=Integer.parseInt(map.get("number").toString());
        List<Dorm> dormList=dormDao.getDorms(dNo,start,number);
        Integer count=dormDao.count(dNo);
        resultMap.put("msg","SUCCESS");
        resultMap.put("code",0);
        resultMap.put("count",count);
        resultMap.put("data",dormList);
        return resultMap;
    }
}
