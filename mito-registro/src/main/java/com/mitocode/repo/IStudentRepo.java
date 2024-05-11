package com.mitocode.repo;

import com.mitocode.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepo extends IGenericRepo<Student, Integer>{
}
