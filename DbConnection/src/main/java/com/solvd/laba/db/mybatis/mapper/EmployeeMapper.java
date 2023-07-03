package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.Employee;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Insert("INSERT INTO employee(name, email, branch_id) VALUES " +
            "(#{name}, #{email}, #{branchId})")
    void insertEmployee(Employee employee);

    @Update("UPDATE employee SET name = #{name}, " +
            "email = #{email}, branch_id = #{branchId} WHERE id = #{id}")
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    void deleteEmployee(int employeeId);

    @Select("SELECT * FROM employee WHERE id = #{id}")
    @Results(id = "selectByIdResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "branchId", column = "branch_id")
    })
    Employee getEmployeeById(int employeeId);

    @Select("SELECT * FROM employee")
    @Results(id = "selectAllResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "branchId", column = "branch_id")
    })
    List<Employee> getAllEmployees();
}
