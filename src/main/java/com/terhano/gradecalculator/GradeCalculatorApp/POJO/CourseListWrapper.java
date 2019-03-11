package com.terhano.gradecalculator.GradeCalculatorApp.POJO;

import java.util.ArrayList;
import java.util.List;

public class CourseListWrapper {
    public List<Course> courses;

    public CourseListWrapper() {
        this.courses = new ArrayList<Course>(20);
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        String list = "";
        for(Course course: courses){
            list += (course.toString()+", ");
        }
        return list;
    }
}
