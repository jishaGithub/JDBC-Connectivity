package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.Customer;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.CustomerMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisCustomerService implements CustomerMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisCustomerService.class);
    private final CustomerMapper customerMapper;
    private SqlSession sqlSession = null;

    public MyBatisCustomerService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.customerMapper = sqlSession.getMapper(CustomerMapper.class);
    }

    public void insertCustomer(Customer customer) {
        try {
            if (customerMapper.getCustomerById(customer.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            customerMapper.insertCustomer(customer);
            logger.info("Insertion complete");
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            if (customerMapper.getCustomerById(customer.getId()) == null) {
                logger.error("Updation Unsuccessful! for customer Id:"+customer.getId());
                throw new RecordNotFoundException(customer.getId(), "Customer");
            }
            customerMapper.updateCustomer(customer);
            logger.info("Updation complete for customer Id:"+customer.getId());
        } catch(RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteCustomer(int customerId) {
        try {
            if (customerMapper.getCustomerById(customerId) == null) {
                logger.error("Deletion Unsuccessful for customerId:" + customerId);
                throw new RecordNotFoundException(customerId, "Customer");
            }
            logger.info("Deletion complete for "+customerId);
            customerMapper.deleteCustomer(customerId);
        } catch(RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Customer getCustomerById(int customerId) {
        try {
            if (customerMapper.getCustomerById(customerId) == null) {
                throw new RecordNotFoundException(customerId, "Customer");
            }
            Customer customer = customerMapper.getCustomerById(customerId);
            logger.info(customer);
            return customerMapper.getCustomerById(customerId);
        } catch(RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        logger.info(customerMapper.getAllCustomers());
        return customerMapper.getAllCustomers();
    }
}
