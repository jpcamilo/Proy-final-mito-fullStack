package com.mitocode.repo;

import com.mitocode.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepo extends IGenericRepo<Course, Integer>{
}
