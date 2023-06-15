package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLBranchDAO;
import com.solvd.laba.db.dao.MySQLDAO.MySQLEmployeeDAO;
import com.solvd.laba.db.model.Employee;
import com.solvd.laba.db.service.DAOService;

public class EmployeeService implements DAOService<Employee> {
    private MySQLEmployeeDAO employeeDAO;

    public EmployeeService(MySQLEmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void findById(int id) {
        employeeDAO.get(id);
    }

    @Override
    public void selectAll() {
        employeeDAO.get();
    }

    @Override
    public void newRow(Employee employee) {
        employeeDAO.add(employee);
    }

    @Override
    public void updateRow(Employee employee, int id) {
        employeeDAO.update(employee,id);
    }

    @Override
    public void deleteRow(int id) {
        employeeDAO.delete(id);
    }
}
