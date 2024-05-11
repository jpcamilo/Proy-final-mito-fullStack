package com.mitocode.controller;

import com.mitocode.dto.CourseDTO;
import com.mitocode.dto.CourseDTO;
import com.mitocode.dto.EnrollmentDTO;
import com.mitocode.model.Course;
import com.mitocode.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll () throws Exception {
        List<CourseDTO> resp =service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception{
        CourseDTO dto = convertToDto(service.readById(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO course) throws Exception {
        CourseDTO resp =  convertToDto(service.save(convertToEntity(course))) ;
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CourseDTO dto) throws Exception{
        Course obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    ////////////////////////////
    private CourseDTO convertToDto(Course obj){
        return mapper.map(obj, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto){
        return mapper.map(dto, Course.class);
    }

}
