package dao;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import pojo.Dorm;

import java.util.List;

public interface DormDao {
    int deleteByPrimaryKey(String dNo);

    int insert(Dorm record);

    int insertSelective(Dorm record);



    List<Dorm> getManDorms(@Param("build") String build);

    List<Dorm> getWomanDorms(@Param("build") String build);

    List<Dorm> getDorms(@Param("dNo") String dNo,@Param("start") Integer start,@Param("number") Integer number);

    Integer count(@Param("dNo") String dNo);

    Boolean deleteAllDorm();
}