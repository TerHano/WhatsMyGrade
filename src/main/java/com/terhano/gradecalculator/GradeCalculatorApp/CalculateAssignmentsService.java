package com.terhano.gradecalculator.GradeCalculatorApp;

import com.terhano.gradecalculator.GradeCalculatorApp.POJO.Assignment;
import com.terhano.gradecalculator.GradeCalculatorApp.POJO.AssignmentListWrapper;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

public class CalculateAssignmentsService {

    static DecimalFormat df = new DecimalFormat("#.##");

    private AssignmentListWrapper assignmentListWrapper;
    private double assignmentGradeTotal;
    private double weightTotal;
    private double totalAssignments;
    private double currentAvg;

    public CalculateAssignmentsService(AssignmentListWrapper assignmentListWrapper) {
        this.assignmentListWrapper = assignmentListWrapper;
        this.assignmentGradeTotal = 0;
        this.weightTotal = 0;
        this.totalAssignments = 0;
        this.currentAvg = 0;
    }

    public double validWeights() throws Exception {
        double total = 0;
        for(Assignment assignment: assignmentListWrapper.assignments) {
            if (assignment.getWeight() != null) {
                if (assignment.getWeight() < 0) throw new Exception("NEGETIVE WEIGHT");
                total += assignment.getDecimalWeight();
            }
        }
        if(total > 1){
            throw new Exception("Invalid weight total");
        }
        setWeightTotal(total);
        return total;
    }


    public double calculateAvg() throws Exception{
        double weightTotal = validWeights();
        double finalGrade = validAssignments();
        finalGrade = (finalGrade/weightTotal);
        setCurrentAvg(finalGrade);
        return Double.parseDouble(df.format(finalGrade));
    }

    public double validAssignments() throws Exception {
        int totalAssignments = 0;
        double assignmentTotal = 0;
        for(Assignment assignment: assignmentListWrapper.assignments){
            if(assignment.getGrade() == null && assignment.getWeight()== null) continue;
            if(assignment.getGrade() == null || assignment.getWeight() == null){
                throw new Exception("empty field in form");
            }
            assignmentTotal += (assignment.getGrade()*assignment.getDecimalWeight());
            totalAssignments++;
        }
        if(totalAssignments == 0) throw new Exception("No assignments entered");
        setAssignmentGradeTotal(assignmentTotal);
        return assignmentTotal;
    }

    public double TargetGradeAssessment(String TargetGrade) throws Exception {
        double assignmentsSoFar = getAssignmentGradeTotal();
        double weightTotal = getWeightTotal();
        double TG = Double.parseDouble(TargetGrade);
        if(weightTotal == 1)
        {
            System.out.println("Weight already 100");
            return 0;
        }
        double lastAssignment = (1-weightTotal);
        return Double.parseDouble(df.format(((TG-assignmentsSoFar)/lastAssignment)));
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public double getAssignmentGradeTotal() {
        return assignmentGradeTotal;
    }

    public void setAssignmentGradeTotal(double assignmentGradeTotal) {
        this.assignmentGradeTotal = assignmentGradeTotal;
    }

    public double getWeightTotal() {
        return weightTotal;
    }

    public void setWeightTotal(double weightTotal) {
        this.weightTotal = weightTotal;
    }

    public double getTotalAssignments() {
        return totalAssignments;
    }

    public void setTotalAssignments(double totalAssignments) {
        this.totalAssignments = totalAssignments;
    }

    public double getCurrentAvg() {
        return currentAvg;
    }

    public void setCurrentAvg(double currentAvg) {
        this.currentAvg = currentAvg;
    }

}
