package com.mitocode.controller;

import com.mitocode.dto.CourseDTO;
import com.mitocode.dto.StudentDTO;
import com.mitocode.model.Student;
import com.mitocode.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll () throws Exception {
        List<StudentDTO> resp =service.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        StudentDTO dto = convertToDto(service.readById(id));
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO student) throws Exception {
        StudentDTO resp =  convertToDto(service.save(convertToEntity(student))) ;
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable("id") Integer id, @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/studentsOrder")
    public  ResponseEntity<List<StudentDTO>> listStudentsORD() throws Exception {
        List<StudentDTO> resp =service.readAll().stream().sorted(Comparator.comparing(Student::getAge).reversed()).map(this::convertToDto).toList();
        return ResponseEntity.ok(resp);
    }
    ////////////////////////////
    private StudentDTO convertToDto(Student obj){
        return mapper.map(obj, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto){
        return mapper.map(dto, Student.class);
    }
}
