package com.emp.management.service;

import com.emp.management.model.Employee;
import com.emp.management.model.Store;
import com.emp.management.model.TimeRange;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Optional<Employee> get(String id);

    public List<TimeRange> getEmployeeTimeRange(String id);

    public Employee create(Employee store);

    public Optional<Employee> update(Employee employee, String id);

    public String delete(String id);
}
