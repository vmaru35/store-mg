package com.emp.management.service.impl;

import com.emp.management.model.Department;
import com.emp.management.repository.DepartmentRepository;
import com.emp.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Optional<Department> get(String id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isEmpty()) {
            return Optional.empty();
        }

        return departmentOptional;
    }

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> update(Department department, String id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isEmpty()) {
            return Optional.empty();
        }

        Department existingDepartment = departmentOptional.get();
        BeanUtils.copyProperties(department, existingDepartment);

        return Optional.of(departmentRepository.save(existingDepartment));
    }

    @Override
    public String delete(String id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isEmpty()) {
            return "Department not found with id: " + id;
        }

        departmentRepository.deleteById(id);

        return "Department Deleted successfully with id: " + id;
    }
}
