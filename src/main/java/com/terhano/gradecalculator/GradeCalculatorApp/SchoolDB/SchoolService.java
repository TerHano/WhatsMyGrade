package com.terhano.gradecalculator.GradeCalculatorApp.SchoolDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SchoolService {

    private final SchoolDB schoolDB;
    private final GradeDB gradeDB;

    private List<String> LetterGrades;

    private HashMap<String,Double> Grades;

    private List<String> LetterstoRemove;

    public void getDefaultScale(int gradeid){
        switch (gradeid){
            case 0:
                Grades.put("Aplus",4.0);
                Grades.put("A",4.0);
                Grades.put("Aminus",3.67);
                Grades.put("Bplus",3.33);
                Grades.put("B",3.00);
                Grades.put("Bminus",2.67);
                Grades.put("Cplus",2.33);
                Grades.put("C",2.00);
                Grades.put("Cminus",1.67);
                Grades.put("Dplus",1.33);
                Grades.put("D",1.00);
                Grades.put("Dminus",.67);
                Grades.put("F",0.0);
                LetterGrades.add("Aplus");
                LetterGrades.add("A");
                LetterGrades.add("Aminus");
                LetterGrades.add("Bplus");
                LetterGrades.add("B");
                LetterGrades.add("Bminus");
                LetterGrades.add("Cplus");
                LetterGrades.add("C");
                LetterGrades.add("Cminus");
                LetterGrades.add("Dplus");
                LetterGrades.add("D");
                LetterGrades.add("Dminus");
                LetterGrades.add("F");
                break;
            default:
                Grades.put("A",4.0);
                Grades.put("Bplus",3.5);
                Grades.put("B",3.00);
                Grades.put("Cplus",2.5);
                Grades.put("C",2.00);
                Grades.put("Dplus",1.5);
                Grades.put("D",1.00);
                Grades.put("F",0.0);
                LetterGrades.add("A");
                LetterGrades.add("Bplus");
                LetterGrades.add("B");
                LetterGrades.add("Cplus");
                LetterGrades.add("C");
                LetterGrades.add("Dplus");
                LetterGrades.add("D");
                LetterGrades.add("F");
                LetterstoRemove.add("Aplus");
                LetterstoRemove.add("Aminus");
                LetterstoRemove.add("Bminus");
                LetterstoRemove.add("Cminus");
                LetterstoRemove.add("Dminus");
                break;
        }
    }


    @Autowired
    public SchoolService(GradeDB gradeDB,SchoolDB schoolDB) {
        LetterGrades = new ArrayList<>();
        LetterstoRemove = new ArrayList<>();
        Grades = new HashMap<>();
        this.gradeDB = gradeDB;
        this.schoolDB = schoolDB;
    }

    public void BuildSchool(int gradeid) throws Exception {
        LetterGrades = new ArrayList<>();
        Grades = new HashMap<>();
        LetterstoRemove = new ArrayList<>();
        if(gradeid < 2) {
            getDefaultScale(gradeid);
            return;
        }
        GradeList gradeList = gradeDB.findById(gradeid).orElse(null);
        if(gradeList == null) throw new Exception("ERR");
        if(gradeList.getAplus()!=null){
            LetterGrades.add("Aplus");
            Grades.put("Aplus", gradeList.getA());
        }
        else{
            LetterstoRemove.add("Aplus");
        }
        if(gradeList.getA()!=null){
            LetterGrades.add("A");
            Grades.put("A", gradeList.getA());
        }
        else{
            LetterstoRemove.add("A");
        }
        if(gradeList.getAminus()!=null){
            LetterGrades.add("Aminus");
            Grades.put("Aminus", gradeList.getAminus());
        }
        else{
            LetterstoRemove.add("Aminus");
        }
        if(gradeList.getBplus()!=null){
            LetterGrades.add("Bplus");
            Grades.put("Bplus", gradeList.getBplus());
        }
        else{
            LetterstoRemove.add("Bplus");
        }
        if(gradeList.getB()!=null){
            LetterGrades.add("B");
            Grades.put("B", gradeList.getB());
        }
        else{
            LetterstoRemove.add("B");
        }
        if(gradeList.getBminus()!=null){
            LetterGrades.add("Bminus");
            Grades.put("Bminus", gradeList.getBminus());
        }
        else{
            LetterstoRemove.add("Bminus");
        }
        if(gradeList.getCplus()!=null){
            LetterGrades.add("Cplus");
            Grades.put("Cplus", gradeList.getCplus());
        }
        else{
            LetterstoRemove.add("Cplus");
        }
        if(gradeList.getC()!=null){
            LetterGrades.add("C");
            Grades.put("C", gradeList.getC());
        }
        else{
            LetterstoRemove.add("C");
        }
        if(gradeList.getCminus()!=null){
            LetterGrades.add("Cminus");
            Grades.put("Cminus", gradeList.getCminus());
        }
        else{
            LetterstoRemove.add("Cminus");
        }
        if(gradeList.getDplus()!=null){
            LetterGrades.add("Dplus");
            Grades.put("Dplus", gradeList.getDplus());
        }
        else{
            LetterstoRemove.add("Dplus");
        }
        if(gradeList.getD()!=null){
            LetterGrades.add("D");
            Grades.put("D", gradeList.getD());
        }
        else{
            LetterstoRemove.add("D");
        }
        if(gradeList.getDminus()!=null){
            LetterGrades.add("Dminus");
            Grades.put("Dminus", gradeList.getDminus());
        }
        else{
            LetterstoRemove.add("Dminus");
        }
        if(gradeList.getF()!=null){
            LetterGrades.add("F");
            Grades.put("F", gradeList.getF());
        }
        else{
            LetterstoRemove.add("F");
        }
    }

    public List<String> getLetterGrades() {
        return LetterGrades;
    }

    public String getLetterGradesString(){
        StringBuilder stringBuilder = new StringBuilder();
        int siz = LetterGrades.size();
        for(int i = 0;i<siz;i++){
            if(i == (siz-1)){
                stringBuilder.append(LetterGrades.get(i));
                break;
            }
            stringBuilder.append(LetterGrades.get(i)).append(",");
        }
        return stringBuilder.toString();
    }

    public String getLetterstoRemove() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<LetterstoRemove.size();i++){
            if(i == LetterstoRemove.size()-1) {
                stringBuilder.append("[value=").append(LetterstoRemove.get(i)).append("]");
                break;
            }
            stringBuilder.append("[value=").append(LetterstoRemove.get(i)).append("],");
        }
        return stringBuilder.toString();
    }

    public HashMap<String, Double> getGrades() {
        return Grades;
    }

    public List<SchoolList> listofSchools(){
        List<SchoolList> schoolLists;
        schoolLists = schoolDB.getListofSchoolsAsc();
        return schoolLists;
    }
}
