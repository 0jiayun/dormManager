package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Students;

import java.util.List;
import java.util.Map;

public interface StudentsDao {
    int deleteByPrimaryKey(String sNo);

    int insert(Students record);

    int insertSelective(Students record);

    Students selectByPrimaryKey(String sNo);

    int updateByPrimaryKeySelective(Students record);

    int updateByPrimaryKey(Students record);

    List<Map<String,String>> getDepts(@Param("sex") String sex);

    List<Students> getStuByDeptAndSex(@Param("dept") String dept,@Param("sex") String sex);

    List<Students> getStudents(@Param("sNo")String sNo,@Param("sName") String sName,
                               @Param("start") Integer start,@Param("number") Integer number);

    Integer count(@Param("sNo")String sNo,@Param("sName") String sName);

    List<Map<String,Object>> getAllDepts();

    int countDeptStudents(@Param("dept") String dept,@Param("sex") String sex );

    Boolean deleteAllStu();

    List<Students> getStudentsBySex(@Param("sex") String sex);//group by dept

    Students studentsLogin(@Param("userName") String userName,@Param("password") String  password);
}