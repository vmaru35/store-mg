package com.emp.management.service;

import com.emp.management.model.Department;
import java.util.Optional;

public interface DepartmentService {

    public Optional<Department> get(String id);

    public Department create(Department store);

    public Optional<Department> update(Department store, String id);

    public String delete(String id);
}
