package com.terhano.gradecalculator.GradeCalculatorApp.SchoolDB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeDB extends CrudRepository<GradeList,Integer> {
}
