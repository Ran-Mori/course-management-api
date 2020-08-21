package com.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.constant.CommonResult;
import com.constant.ErrorCode;
import com.entity.TblCourse;
import com.entity.TblCourseTime;
import com.service.CourseService;
import com.service.CourseTimeService;
import com.vo.CourseTotalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTimeService courseTimeService;

    @RequestMapping(value = "/course",method = RequestMethod.POST)
    public CommonResult insertCourse(@RequestBody Map<String,Object> data){
        //获取Object未转化的数据
        Object courseObj;
        Object courseTimesObj;
        try {
            courseObj=data.get("course");
            courseTimesObj=data.get("courseTimes");
        }catch (Exception e){
            return CommonResult.fail(ErrorCode.PARSE_REQUEST_ERROR);
        }

        //判断获取数据是否为空
        if (courseObj==null||courseTimesObj==null)
            return CommonResult.fail(ErrorCode.REQUEST_NULL);

        //使用Hutool解析封装成Javabean对象
        JSONObject courseJSONObj = JSONUtil.parseObj(courseObj);
        JSONArray courseTimesJSONArr = JSONUtil.parseArray(courseTimesObj);

        TblCourse course = JSONUtil.toBean(courseJSONObj, TblCourse.class);
        List<TblCourseTime> courseTimeList = JSONUtil.toList(courseTimesJSONArr, TblCourseTime.class);

        //执行插入操作
        try {
            int i = courseService.insertCourse(course);
            if (i!=1)
                return CommonResult.fail(ErrorCode.INSERT_ERROR);

            Integer courseId = course.getId();
            for (TblCourseTime courseTime: courseTimeList) {
                courseTime.setCourseId(courseId);
                int j = courseTimeService.insertCourseTime(courseTime);
                if (j!=1)
                    return CommonResult.fail(ErrorCode.INSERT_ERROR);
            }
        }catch (Exception e){
            return CommonResult.fail(ErrorCode.INSERT_ERROR);
        }
        return CommonResult.success();
    }

    @RequestMapping(value = "/course",method = RequestMethod.GET)
    public CommonResult selectCoursesByUserId(@RequestParam("userId") Integer userId){
        //判断请求域的数据是否为空
        if (userId==null)
            return CommonResult.fail(ErrorCode.REQUEST_NULL);

        //根据userId查询对应的课程
        List<TblCourse> courseList;
        try {
            courseList = courseService.selectCoursesByUserId(userId);
        }catch (Exception e){
            return CommonResult.fail(ErrorCode.SELECT_ERROR);
        }
        if (courseList==null)
            return CommonResult.fail(ErrorCode.SELECT_ERROR);

        //设置最后返回的courses数组
        List<CourseTotalVO> courses=new LinkedList<>();

        //根据每个courseId查询对应课程的时间，并封装到courses里面
        for (TblCourse tblCourse: courseList) {
            //获取courseId
            Integer courseId = tblCourse.getId();

            //根据courseId查询对应的时间
            List<TblCourseTime> courseTimeList;
            try {
                courseTimeList = courseTimeService.selectCourseTimesByCourseId(courseId);
            }catch (Exception e){
                return CommonResult.fail(ErrorCode.SELECT_ERROR);
            }
            if (courseTimeList==null)
                return CommonResult.fail(ErrorCode.SELECT_ERROR);

            //新建一个VO包装类,填入参数并疯转
            CourseTotalVO courseTotalVO = new CourseTotalVO();
            courseTotalVO.setCourse(tblCourse);
            courseTotalVO.setCourseTimeList(courseTimeList);
            courses.add(courseTotalVO);
        }

        return CommonResult.success().add("courses",courses);
    }
}
