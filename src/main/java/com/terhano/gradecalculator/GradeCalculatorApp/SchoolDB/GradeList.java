package com.terhano.gradecalculator.GradeCalculatorApp.SchoolDB;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gradelist")
public class GradeList {
    @Id
    private int gradeid;

    private Double Aplus;
    private Double A;
    private Double Aminus;
    private Double Bplus;
    private Double B;
    private Double Bminus;
    private Double Cplus;
    private Double C;
    private Double Cminus;
    private Double Dplus;
    private Double D;
    private Double Dminus;
    private Double F;

    public int getgradeId() {
        return gradeid;
    }

    public void setgradeId(int id) {
        this.gradeid = id;
    }

    public Double getAplus() {
        return Aplus;
    }

    public void setAplus(Double aplus) {
        Aplus = aplus;
    }

    public Double getA() {
        return A;
    }

    public void setA(Double a) {
        A = a;
    }

    public Double getAminus() {
        return Aminus;
    }

    public void setAminus(Double aminus) {
        Aminus = aminus;
    }

    public Double getBplus() {
        return Bplus;
    }

    public void setBplus(Double bplus) {
        Bplus = bplus;
    }

    public Double getB() {
        return B;
    }

    public void setB(Double b) {
        B = b;
    }

    public Double getBminus() {
        return Bminus;
    }

    public void setBminus(Double bminus) {
        Bminus = bminus;
    }

    public Double getCplus() {
        return Cplus;
    }

    public void setCplus(Double cplus) {
        Cplus = cplus;
    }

    public Double getC() {
        return C;
    }

    public void setC(Double c) {
        C = c;
    }

    public Double getCminus() {
        return Cminus;
    }

    public void setCminus(Double cminus) {
        Cminus = cminus;
    }

    public Double getDplus() {
        return Dplus;
    }

    public void setDplus(Double dplus) {
        Dplus = dplus;
    }

    public Double getD() {
        return D;
    }

    public void setD(Double d) {
        D = d;
    }

    public Double getDminus() {
        return Dminus;
    }

    public void setDminus(Double dminus) {
        Dminus = dminus;
    }

    public Double getF() {
        return F;
    }

    public void setF(Double f) {
        F = f;
    }


}
