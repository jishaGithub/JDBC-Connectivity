package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Branch;
import java.sql.*;
import java.util.ArrayList;

public class MySQLBranchDAO extends AbstractDAO<Branch> {
    public MySQLBranchDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    @Override
    public Boolean findById(int id1) {
        System.out.println("Finding record of ID:" + id1 + ".....");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM branch where id=?");
            preparedStatement.setInt(1, id1);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
                int branchId = result.getInt("id");
                String branchName = result.getString("branch_name");
                int branchLocationId = result.getInt("branch_location_id");
                Branch branch = new Branch(branchId, branchName, branchLocationId);
                System.out.println(branch);
                System.out.println(" ");
                return true;
            } else {
                System.out.println("No record found. Invalid ID");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL query error");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Branch> selectAll() {
            System.out.println("Displaying all the rows from branch table");
            ArrayList<Branch> branches = new ArrayList<>();
            try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM branch");
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    int branchId = result.getInt("id");
                    String branchName = result.getString("branch_name");
                    int branchLocationId = result.getInt("branch_location_id");
                    Branch branch = new Branch(branchId,branchName, branchLocationId);
                    branches.add(branch);
                }
                System.out.println(branches);
                return branches;
            } catch (SQLException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            return null;
    }

    @Override
    public void addNewRow(Branch row) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO branch (branch_name, branch_location_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setString(1, row.getBranchName());
            preparedStatement.setInt(2, row.getBranchLocationId());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
            System.out.println(selectAll());
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    @Override
    public void update(Branch branch, int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String updateStatement = "UPDATE branch SET branch_name=?, branch_location_id=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setString(1, branch.getBranchName());
            preparedStatement.setInt(2, branch.getBranchLocationId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
            System.out.println(selectAll());
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(id)) {
                String deleteStatement = "DELETE from branch WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
                System.out.println(selectAll());
            }
        } catch (SQLException e) {
            System.out.println("Deletion unsuccessful!");
            e.printStackTrace();
        }
    }
}
