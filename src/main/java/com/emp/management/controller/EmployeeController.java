package com.emp.management.controller;

import com.emp.management.model.Employee;
import com.emp.management.model.Store;
import com.emp.management.model.TimeRange;
import com.emp.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity getEmp(@PathVariable("id") String id){
        return ResponseEntity.of(employeeService.get(id));
    }

    @GetMapping("/times/{id}")
    public ResponseEntity getEmpTimes(@PathVariable("id") String id){
        List<TimeRange> ranges = employeeService.getEmployeeTimeRange(id);
        if (ranges == null) {
            return ResponseEntity.badRequest().body("Employee not found with id: " + id);
        }
        return ResponseEntity.accepted().body(ranges);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.accepted().body(employeeService.create(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@RequestBody Employee employee, @PathVariable("id") String id) {
        Optional<Employee> employeeOptional = employeeService.update(employee, id);

        if (employeeOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Employee not found with id: " + id);
        }

        return ResponseEntity.accepted().body(employeeOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") String id){
        return ResponseEntity.accepted().body(employeeService.delete(id));
    }


}
