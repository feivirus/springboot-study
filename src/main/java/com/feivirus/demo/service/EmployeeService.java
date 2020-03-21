package com.feivirus.demo.service;

import com.feivirus.demo.dao.EmployeeMapper;
import com.feivirus.demo.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author feivirus
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public void addEmployee(String name) {
        Employee employee = new Employee();
        employee.setAge(10);
        employee.setName(name);
        employeeMapper.insertSelective(employee);
    }

}
