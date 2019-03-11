package com.terhano.gradecalculator.GradeCalculatorApp.POJO;

public class Course {
    String grade;
    Double credit;

    public Course() {
        this.grade = "";
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "[G: "+grade+", C: "+credit+"]";
    }
}
