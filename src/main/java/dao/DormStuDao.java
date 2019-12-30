package dao;

import org.apache.ibatis.annotations.Param;
import pojo.DormStu;
import pojo.StuDorm;

import java.util.List;

public interface DormStuDao {
    int insert(DormStu record);

    int insertSelective(DormStu record);

    Boolean deleteAllDS();

    List<StuDorm> getArrange(@Param("sNo")String sNo, @Param("sName") String sName,@Param("dNo") String dNo,
                             @Param("start") Integer start, @Param("number") Integer number);
    int count(@Param("sNo")String sNo, @Param("sName") String sName,@Param("dNo") String dNo);

    List<StuDorm> getAllArranges();
}