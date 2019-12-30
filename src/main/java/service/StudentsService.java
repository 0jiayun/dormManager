package service;

import org.springframework.stereotype.Service;
import pojo.Students;

import java.util.List;
import java.util.Map;


public interface StudentsService {
    Map insertStudents(List<Students> list);

    Map getStudents(Map<String ,Object> map);

    Map countDeptStudents();
}
