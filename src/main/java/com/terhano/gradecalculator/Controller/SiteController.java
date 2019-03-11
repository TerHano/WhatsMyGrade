package com.terhano.gradecalculator.Controller;


import com.terhano.gradecalculator.GradeCalculatorApp.CalculateAssignmentsService;
import com.terhano.gradecalculator.GradeCalculatorApp.CalculateGPAService;
import com.terhano.gradecalculator.GradeCalculatorApp.POJO.Assignment;
import com.terhano.gradecalculator.GradeCalculatorApp.POJO.AssignmentListWrapper;
import com.terhano.gradecalculator.GradeCalculatorApp.POJO.Course;
import com.terhano.gradecalculator.GradeCalculatorApp.POJO.CourseListWrapper;
import com.terhano.gradecalculator.GradeCalculatorApp.SchoolDB.SchoolList;
import com.terhano.gradecalculator.GradeCalculatorApp.SchoolDB.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SiteController {

    @Autowired
    SchoolService schoolService;
    @Autowired
    CalculateGPAService calculateGPAService;

    @RequestMapping("/")
    public String Home(Model model) {
        List<SchoolList> schoolLists = schoolService.listofSchools();
        AssignmentListWrapper assignmentListWrapper = new AssignmentListWrapper();
        CourseListWrapper courseListWrapper = new CourseListWrapper();
        for (int i = 0; i < 20; i++) {
            assignmentListWrapper.addAssignment(new Assignment());
            courseListWrapper.addCourse(new Course());
        }
        model.addAttribute("assignlist", assignmentListWrapper);
        model.addAttribute("courselist", courseListWrapper);
        model.addAttribute("schoollist", schoolLists);
        return "Home";
    }

    @ResponseBody
    @RequestMapping(value = "/getgrades", method = RequestMethod.POST)
    public String getGrades(@RequestParam(value = "gradeid") String gradeid) throws Exception {
        calculateGPAService.initializeSchool(Integer.parseInt(gradeid));
        return schoolService.getLetterstoRemove();
    }

    @RequestMapping(value = "/gradepost", method = RequestMethod.POST)
    public String calculateGrade(@ModelAttribute AssignmentListWrapper assignlist,
                                 @RequestParam(value = "TargetGrade") String TargetGrade, Model model) throws Exception {
        CalculateAssignmentsService CAS = new CalculateAssignmentsService(assignlist);
        System.out.println(assignlist.toString());
        System.out.println(TargetGrade);
        double avg = 0;
        try {
            avg = CAS.calculateAvg();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        if (!TargetGrade.isEmpty()) {
            double AG = CAS.TargetGradeAssessment(TargetGrade);
            model.addAttribute("AG", AG);
            model.addAttribute("TargetGrade", TargetGrade);
        }
        model.addAttribute("GR", avg);
        return "fragments :: GradeResult";
    }

    @RequestMapping(value = "/gpapost", method = RequestMethod.POST)
    public String calculateGrade(@ModelAttribute CourseListWrapper courselist,
                                 @RequestParam(value = "TotalCredits") String TotalCredits,
                                 @RequestParam(value = "TotalGPA") String TotalGPA,
                                 @RequestParam(value = "gradeid") String GradeID,
                                 Model model) throws Exception {
        System.out.println(courselist.toString());
        if(schoolService.getLetterGrades().isEmpty()) calculateGPAService.initializeSchool(Integer.parseInt(GradeID));
        calculateGPAService.setCourses(courselist);
        String gpa;
        try {
            gpa = calculateGPAService.evaluateGPA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        if (!TotalCredits.isEmpty() && !TotalGPA.isEmpty()) {
            String CumGPA = calculateGPAService.evaluateCumulativeGPA(Double.parseDouble(TotalGPA), Double.parseDouble(TotalCredits));
            model.addAttribute("CumGPA", CumGPA);
        }
//        if(TotalCredits.isEmpty()  TotalGPA.isEmpty()){
//            throw new Exception("Optional CumGPA not completed right!");
//        }
        model.addAttribute("GPA", gpa);
        return "fragments :: GPAResult";

    }
}
