package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.Employee;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisEmployeeService implements EmployeeMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisEmployeeService.class);
    private final EmployeeMapper employeeMapper;
    private SqlSession sqlSession = null;

    public MyBatisEmployeeService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    }

    public void insertEmployee(Employee employee) {
        try {
            if (employeeMapper.getEmployeeById(employee.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            employeeMapper.insertEmployee(employee);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            if (employeeMapper.getEmployeeById(employee.getId()) == null) {
                logger.error("Updation Unsuccessful! for employee Id: " + employee.getId());
                throw new RecordNotFoundException(employee.getId(), "Employee");
            }
            employeeMapper.updateEmployee(employee);
            logger.info("Updation complete for employee Id: " + employee.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("branch_id is not valid."+e.getMessage());
        }
    }

    public void deleteEmployee(int employeeId) {
        try {
            if (employeeMapper.getEmployeeById(employeeId) == null) {
                logger.error("Deletion Unsuccessful for employee Id: " + employeeId);
                throw new RecordNotFoundException(employeeId, "Employee");
            }
            logger.info("Deletion complete for employee Id: " + employeeId);
            employeeMapper.deleteEmployee(employeeId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Employee getEmployeeById(int employeeId) {
        try {
            if (employeeMapper.getEmployeeById(employeeId) == null) {
                throw new RecordNotFoundException(employeeId, "Employee");
            }
            Employee employee = employeeMapper.getEmployeeById(employeeId);
            logger.info(employee);
            return employee;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        logger.info(employeeMapper.getAllEmployees());
        return employeeMapper.getAllEmployees();
    }
}
