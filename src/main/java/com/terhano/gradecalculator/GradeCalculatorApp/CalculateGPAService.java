package com.terhano.gradecalculator.GradeCalculatorApp;

import com.terhano.gradecalculator.GradeCalculatorApp.POJO.AssignmentListWrapper;
import com.terhano.gradecalculator.GradeCalculatorApp.POJO.Course;
import com.terhano.gradecalculator.GradeCalculatorApp.POJO.CourseListWrapper;
import com.terhano.gradecalculator.GradeCalculatorApp.SchoolDB.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;

@Service
public class CalculateGPAService {

    final
    SchoolService schoolService;

    private static DecimalFormat df = new DecimalFormat("#.00");

    private CourseListWrapper courseListWrapper;
    private HashMap<String,Double> GradeScale;
    private double creditTotal;
    private double gradepointTotal;


    @Autowired
    public CalculateGPAService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    public void initializeSchool(int GradeID) throws Exception {
        schoolService.BuildSchool(GradeID);
    }

    public void setCourses(CourseListWrapper courseListWrapper) {
        this.courseListWrapper = courseListWrapper;
        this.GradeScale = schoolService.getGrades();
    }


    public String evaluateGPA() throws Exception {
        //schoolService.BuildSchool(id);
        double totalCredits = 0;
        double gradePoints = 0;
        for(Course course: courseListWrapper.courses){
            if(!course.getGrade().equals("") && course.getCredit()!= null){
                totalCredits += course.getCredit();
                gradePoints += (course.getCredit()*GradeValue(course.getGrade()));
            }
        }
        if(totalCredits == 0){
            throw new Exception("NO COURSES INPUTTED");
        }
        setCreditTotal(totalCredits);
        setGradepointTotal(gradePoints);
        double gpa = gradePoints/totalCredits;
        String gpastr = df.format(gpa);
        return gpastr;
    }

    public String evaluateCumulativeGPA(double pastGPA, double pastCredits){
        double pastGP = pastCredits*pastGPA;
        double totalGP = pastGP + getGradepointTotal();
        double totalCredits = pastCredits + getCreditTotal();
        return df.format(totalGP/totalCredits);
    }

    public double getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(double creditTotal) {
        this.creditTotal = creditTotal;
    }

    public double getGradepointTotal() {
        return gradepointTotal;
    }

    public void setGradepointTotal(double gradepointTotal) {
        this.gradepointTotal = gradepointTotal;
    }

    public double GradeValue(String Grade){
        return GradeScale.get(Grade);
    }
}
