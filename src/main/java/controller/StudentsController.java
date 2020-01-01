package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Students;
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

    @RequestMapping("login")
    @ResponseBody
    public Map studentsLogin(@RequestBody Map<String,Object> map){
        return studentsService.studentsLogin(map);
    }

    @RequestMapping("update")
    @ResponseBody
    public Map update(@RequestBody Students students){
        return studentsService.updateStudents(students);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map delete(@RequestParam("sNo") String sNo){
        return studentsService.deleteStudents(sNo);
    }
}
