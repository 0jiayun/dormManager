package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pojo.Dorm;
import pojo.StuDorm;
import pojo.Students;
import service.DormService;
import service.DormStuService;
import service.StudentsService;
import utils.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("easyPoi")
public class ExcelController {
    @Autowired
    private StudentsService studentsService;

    @Autowired
    private DormService dormService;

    @Autowired
    private DormStuService dormStuService;

//    @RequestMapping("export")
//    public void export(HttpServletResponse response){
//
//        //模拟从数据库获取需要导出的数
//        ResultUtil sites = siteService.getSites(1, 8, new Site());
//        List data = (List) sites.getData();
//
//
//        //导出操作 title带表的是表头就是第一行是标题，而不是表头。sheetName指的多sheet时的名字，设置为Null显示为sheet0，
//        FileUtil.exportExcel(data,null,null,Site.class,"测站信息.xls",true,response);
//    }

    @RequestMapping("exportArrange")
    @ResponseBody
    public void exportArrange(HttpServletResponse response){
        List<StuDorm> list=dormStuService.getStuDorms();
        FileUtil.exportExcel(list,null,null,StuDorm.class,"宿舍安排表.xls",true,response);


        //TODO 保存数据库
    }

    @RequestMapping("importExcelS")
    @ResponseBody
    public Map importExcelS(MultipartFile file){
        //解析excel，titleRows指的是上面设置的title如果上面设置了，就要写成1代表第一行是标题，
        //写成0代表没有标题headerRows代表表头，当没有标题的时候这个数值设置成1代表第一行是表头，数据在第二行
        List<Students> list = FileUtil.importExcel(file,0,1,Students.class);

        return studentsService.insertStudents(list);
        //TODO 保存数据库
    }

    //导入宿舍信息
    @RequestMapping("importExcelD")
    @ResponseBody
    public Map importExcelD(MultipartFile file){
        //解析excel，titleRows指的是上面设置的title如果上面设置了，就要写成1代表第一行是标题，
        //写成0代表没有标题headerRows代表表头，当没有标题的时候这个数值设置成1代表第一行是表头，数据在第二行
        List<Dorm> list = FileUtil.importExcel(file,0,1,Dorm.class);

        return dormService.insertDorm(list);
        //TODO 保存数据库
    }



}
