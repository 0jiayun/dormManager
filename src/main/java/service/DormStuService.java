package service;

import pojo.StuDorm;

import java.util.List;
import java.util.Map;

public interface DormStuService {
    Map insertDormStu();

    Map getArrange(Map<String,Object> map);

    List<StuDorm> getStuDorms();

    Map deleteAll();

    Map getArrangebySno(String sNo);
}
