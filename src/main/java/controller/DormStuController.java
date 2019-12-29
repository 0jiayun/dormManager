package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DormStuService;

import java.util.Map;

@Controller
@RequestMapping("DormStu")
public class DormStuController {
    @Autowired
    private DormStuService dormStuService;

    /**
     * 获取宿舍安排
     * @return
     */
    @RequestMapping("getArrange")
    @ResponseBody
    public Map arrangeDorm(@RequestBody Map<String ,Object> map){
        return dormStuService.getArrange(map);
    }

    /**
     * 安排宿舍
     * @return
     */
    @RequestMapping("arrangeDorm")
    @ResponseBody
    public Map arrangeDorm(){
        return dormStuService.insertDormStu();
    }

    /**
     * 清理所有数据
     * @return
     */
    @RequestMapping("clearAll")
    @ResponseBody
    public Map clearAll(){
        return dormStuService.deleteAll();
    }

}
