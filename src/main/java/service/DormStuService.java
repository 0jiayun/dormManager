package service;

import java.util.Map;

public interface DormStuService {
    Map insertDormStu();

    Map getArrange(Map<String,Object> map);

    Map deleteAll();
}
