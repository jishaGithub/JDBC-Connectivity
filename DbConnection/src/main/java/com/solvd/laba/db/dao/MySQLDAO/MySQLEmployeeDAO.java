package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class MySQLEmployeeDAO extends AbstractDAO<Employee> {
    private static final Logger logger = LogManager.getLogger(MySQLEmployeeDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM employee WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM employee";
    private final static String INSERT_VALUES = "INSERT INTO employee (name, email, branch_id) VALUES (?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE employee SET name=?, email=?, branch_id=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM employee WHERE id=?";

    @Override
    public Employee get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int employeeId = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                int branchId = result.getInt("branch_id");
                return new Employee(employeeId, name, email, branchId);
            } else {
                logger.info("No record found. Invalid ID");
            }
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Employee> get() {
        logger.info("Displaying all the rows from employee table");
        ArrayList<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int employeeId = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                int branchId = result.getInt("branch_id");
                Employee employee = new Employee(employeeId, name, email, branchId);
                employees.add(employee);
            }
            logger.info(employees);
            return employees;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Employee employee) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3, employee.getBranchId());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3, employee.getBranchId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            logger.info("Update complete");
        } catch (SQLIntegrityConstraintViolationException si) {
            logger.info("Error! branch_id:"+employee.getBranchId()+" is null in branch table.Use another non-null branch id");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_VALUE)) {
            if (get(id) != null) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                logger.info("Deletion complete");
            }
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }
}
