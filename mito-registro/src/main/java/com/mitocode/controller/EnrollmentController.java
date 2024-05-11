package com.mitocode.controller;

import com.mitocode.dto.EnrollmentDTO;
import com.mitocode.model.Course;
import com.mitocode.model.Enrollment;
import com.mitocode.model.EnrrollmentDetail;
import com.mitocode.model.Student;
import com.mitocode.service.ICourseService;
import com.mitocode.service.IEnrollmentService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.groupingByConcurrent;


@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    private final IEnrollmentService service;

    private  final ICourseService serviceCourse;

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> readAll() throws Exception{
        List<EnrollmentDTO> list = service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        EnrollmentDTO dto = convertToDto(service.readById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> save(@Valid @RequestBody EnrollmentDTO dto) throws Exception{
        Enrollment obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody EnrollmentDTO dto) throws Exception{
        Enrollment obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/studentsCourses")
    public ResponseEntity<Object> getStudentsCourses() throws Exception {
        List<Enrollment> list = service.readAll().stream().toList();
        List<Course> listCourse = serviceCourse.readAll().stream().toList();
        HashMap<String, List<String>> resp = new HashMap<>();

        listCourse.forEach(course -> {
            List<String> students = new ArrayList<>();
            list.forEach(enrollment -> {
                 if (enrollment.getDetails().stream().anyMatch(detailCourse -> detailCourse.getCourse().equals(course)))
                    students.add(enrollment.getStudent().getNames());
                    }
            );
         resp.put(course.getName(),students);
        });

        return ResponseEntity.ok(resp);
    }

    ////////////////////////////
    private EnrollmentDTO convertToDto(Enrollment obj){
        return modelMapper.map(obj, EnrollmentDTO.class);
    }

    private Enrollment convertToEntity(EnrollmentDTO dto){
        return modelMapper.map(dto, Enrollment.class);
    }



}
