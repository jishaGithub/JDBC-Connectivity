package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.CustomerAuthentication;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.CustomerAuthenticationMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisCustomerAuthenticationService implements CustomerAuthenticationMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisCustomerAuthenticationService.class);
    private final CustomerAuthenticationMapper customerAuthenticationMapper;
    private SqlSession sqlSession = null;

    public MyBatisCustomerAuthenticationService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.customerAuthenticationMapper = sqlSession.getMapper(CustomerAuthenticationMapper.class);
    }

    public void insertCustomerAuthentication(CustomerAuthentication customerAuthentication) {
        try {
            if (customerAuthenticationMapper.getCustomerAuthenticationById(customerAuthentication.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            customerAuthenticationMapper.insertCustomerAuthentication(customerAuthentication);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateCustomerAuthentication(CustomerAuthentication customerAuthentication) {
        try {
            if (customerAuthenticationMapper.getCustomerAuthenticationById(customerAuthentication.getId()) == null) {
                logger.error("Updation Unsuccessful! for customer authentication Id: " + customerAuthentication.getId());
                throw new RecordNotFoundException(customerAuthentication.getId(), "CustomerAuthentication");
            }
            customerAuthenticationMapper.updateCustomerAuthentication(customerAuthentication);
            logger.info("Updation complete for customer authentication Id: " + customerAuthentication.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("customer_id is not valid" + e.getMessage());
        }
    }

    public void deleteCustomerAuthentication(int customerAuthenticationId) {
        try {
            if (customerAuthenticationMapper.getCustomerAuthenticationById(customerAuthenticationId) == null) {
                logger.error("Deletion Unsuccessful for customer authentication Id: " + customerAuthenticationId);
                throw new RecordNotFoundException(customerAuthenticationId, "CustomerAuthentication");
            }
            logger.info("Deletion complete for customer authentication Id: " + customerAuthenticationId);
            customerAuthenticationMapper.deleteCustomerAuthentication(customerAuthenticationId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public CustomerAuthentication getCustomerAuthenticationById(int customerAuthenticationId) {
        try {
            if (customerAuthenticationMapper.getCustomerAuthenticationById(customerAuthenticationId) == null) {
                throw new RecordNotFoundException(customerAuthenticationId, "CustomerAuthentication");
            }
            CustomerAuthentication customerAuthentication = customerAuthenticationMapper.getCustomerAuthenticationById(customerAuthenticationId);
            logger.info(customerAuthentication);
            return customerAuthentication;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<CustomerAuthentication> getAllCustomerAuthentications() {
        logger.info(customerAuthenticationMapper.getAllCustomerAuthentications());
        return customerAuthenticationMapper.getAllCustomerAuthentications();
    }
}
