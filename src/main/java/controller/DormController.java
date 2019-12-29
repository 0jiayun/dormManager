package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DormService;

import java.util.Map;

@Controller
@RequestMapping("dorm")
public class DormController {
    @Autowired
    private DormService dormService;

    @RequestMapping("getDorms")
    @ResponseBody
    public Map getDorms(@RequestBody Map<String,Object> map){
        return dormService.getDorms(map);
    }
}
