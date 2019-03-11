package com.terhano.gradecalculator.GradeCalculatorApp.SchoolDB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SchoolDB extends CrudRepository<SchoolList, Integer> {

    @Query(nativeQuery = true,value = "select * from SchoolList where (id>1) order by name")
    List<SchoolList> getListofSchoolsAsc();


}
