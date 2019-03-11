package com.terhano.gradecalculator.GradeCalculatorApp.POJO;

public class Assignment {
    private Double grade;
    private Double weight;


    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Double getWeight() {
        return weight;
    }
    
    public Double getDecimalWeight(){
        return (weight/100);
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[G: "+grade+", W: "+weight+"]";
    }
}
