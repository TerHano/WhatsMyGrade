package com.terhano.gradecalculator.GradeCalculatorApp.POJO;

import java.util.ArrayList;
import java.util.List;

public class AssignmentListWrapper {

    public List<Assignment> assignments;

    public AssignmentListWrapper() {
        this.assignments = new ArrayList<Assignment>(20);
    }

    public void addAssignment(Assignment assignment){
        this.assignments.add(assignment);
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        String list = "";
        for(Assignment assignment: assignments){
            list += (assignment.toString()+", ");
        }
         return list;
    }
}
