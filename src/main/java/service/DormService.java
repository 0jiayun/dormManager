package service;

import pojo.Dorm;

import java.util.List;
import java.util.Map;

public interface DormService {
    Map insertDorm(List<Dorm> list);

    Map getDorms(Map<String,Object> map);
}
