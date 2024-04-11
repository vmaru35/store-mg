package com.emp.management.controller;

import com.emp.management.model.Department;
import com.emp.management.model.Employee;
import com.emp.management.model.Store;
import com.emp.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity getDepartment(@PathVariable("id") String id){
        return ResponseEntity.of(departmentService.get(id));
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.accepted().body(departmentService.create(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDepartment(@RequestBody Department department, @PathVariable("id") String id) {
        Optional<Department> departmentOptional = departmentService.update(department, id);

        if (departmentOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Department not found with id: " + id);
        }

        return ResponseEntity.accepted().body(departmentOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable("id") String id){
        return ResponseEntity.accepted().body(departmentService.delete(id));
    }


}
