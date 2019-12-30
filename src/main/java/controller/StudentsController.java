package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StudentsService;

import java.util.Map;

@Controller
@RequestMapping("students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @RequestMapping("getStudents")
    @ResponseBody
    public Map getStudents(@RequestBody Map<String,Object> map){
        return studentsService.getStudents(map);
    }

    @RequestMapping("stuShow")
    @ResponseBody
    public Map stuShow(){
        return studentsService.countDeptStudents();
    }
}
