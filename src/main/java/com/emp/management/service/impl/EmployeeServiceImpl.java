package com.emp.management.service.impl;

import com.emp.management.model.Department;
import com.emp.management.model.Employee;
import com.emp.management.model.Store;
import com.emp.management.model.TimeRange;
import com.emp.management.repository.DepartmentRepository;
import com.emp.management.repository.EmployeeRepository;
import com.emp.management.service.DepartmentService;
import com.emp.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Optional<Employee> get(String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            return Optional.empty();
        }

        return employeeOptional;
    }

    @Override
    public List<TimeRange> getEmployeeTimeRange(String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            return null;
        }

        List<TimeRange> timeRanges = new ArrayList<>();

        for (String deptId: employeeOptional.get().getDepartments()) {
            Optional<Department> departmentOptional = departmentService.get(deptId);
            departmentOptional.ifPresent(department -> timeRanges.add(department.getRange()));
        }

        return timeRanges;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> update(Employee employee, String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            return Optional.empty();
        }

        Employee existingEmployee = employeeOptional.get();
        BeanUtils.copyProperties(employee, existingEmployee);

        return Optional.of(employeeRepository.save(existingEmployee));
    }

    @Override
    public String delete(String id) {
        return null;
    }
}
