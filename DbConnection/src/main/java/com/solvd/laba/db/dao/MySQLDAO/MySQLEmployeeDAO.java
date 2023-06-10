package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Employee;
import java.sql.*;
import java.util.ArrayList;

public class MySQLEmployeeDAO extends AbstractDAO<Employee> {    
    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM employee WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int employeeId = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                int branchId = result.getInt("branch_id");
                Employee employee = new Employee(employeeId, name, email, branchId);
                System.out.println(employee);
                System.out.println();
                return true;
            } else {
                System.out.println("No record found. Invalid ID");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Employee> selectAll() {
        System.out.println("Displaying all the rows from employee table");
        ArrayList<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM employee")) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int employeeId = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                int branchId = result.getInt("branch_id");
                Employee employee = new Employee(employeeId, name, email, branchId);
                employees.add(employee);
            }
            System.out.println(employees);
            return employees;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(Employee employee) {
        String insertValueStatement = "INSERT INTO employee (name, email, branch_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertValueStatement)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3, employee.getBranchId());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee, int id) {
        String updateStatement = "UPDATE employee SET name=?, email=?, branch_id=? WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateStatement)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3, employee.getBranchId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLIntegrityConstraintViolationException si) {
            System.out.println("Error! branch_id:"+employee.getBranchId()+" is null in branch table.Use another non-null branch id");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String deleteStatement = "DELETE FROM employee WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteStatement)) {
            if (findById(id)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }
}
