package com.solvd.laba.db.service.jdbc;

import com.solvd.laba.db.dao.EmployeeDAO;
import com.solvd.laba.db.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class EmployeeService implements GenericDAO<Employee> {
    private static final Logger logger = LogManager.getLogger(EmployeeService.class);
    private EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee get(int id) {
        return employeeDAO.get(id);
    }

    @Override
    public ArrayList<Employee> get() {
        return employeeDAO.get();
    }

    @Override
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    @Override
    public void update(Employee employee, int id) {
        employeeDAO.update(employee, id);
    }

    @Override
    public void delete(int id) {
        employeeDAO.delete(id);
    }
}
