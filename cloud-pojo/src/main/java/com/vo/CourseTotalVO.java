package com.vo;

import com.entity.TblCourse;
import com.entity.TblCourseTime;

import java.util.List;

public class CourseTotalVO {
    private TblCourse course;
    List<TblCourseTime> courseTimeList;

    public TblCourse getCourse() {
        return course;
    }

    public void setCourse(TblCourse course) {
        this.course = course;
    }

    public List<TblCourseTime> getCourseTimeList() {
        return courseTimeList;
    }

    public void setCourseTimeList(List<TblCourseTime> courseTimeList) {
        this.courseTimeList = courseTimeList;
    }
}
