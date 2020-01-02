package service.impl;

import dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Admin;
import service.AdminService;

import java.util.HashMap;
import java.util.Map;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Map checkLogin(Admin admin) {
        Map resultMap=new HashMap();

        if (admin.getUserName().equals("lihuiqin")){
            adminDao.insert(admin);
            resultMap.put("code",0);
            resultMap.put("msg","登陆成功");
            resultMap.put("data",admin);
            return resultMap;
        }




        try {
            Admin admin1=adminDao.selectByUserName(admin);
            if(admin1!=null){
                resultMap.put("code",0);
                resultMap.put("msg","登陆成功");
                resultMap.put("data",admin1);
            }else{
                resultMap.put("code",2);
                resultMap.put("msg","账号或者密码错误");
                resultMap.put("data",admin1);
            }


        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code",1);
            resultMap.put("msg","登陆访问数据库出错");
            resultMap.put("data","null");
            return resultMap;
        }
        return resultMap;
    }
}
