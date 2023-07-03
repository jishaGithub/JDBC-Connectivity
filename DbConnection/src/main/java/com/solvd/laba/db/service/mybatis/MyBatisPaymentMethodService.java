package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.PaymentMethod;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.PaymentMethodMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyBatisPaymentMethodService implements PaymentMethodMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisPaymentMethodService.class);
    private final PaymentMethodMapper paymentMethodMapper;
    private SqlSession sqlSession = null;

    public MyBatisPaymentMethodService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.paymentMethodMapper = sqlSession.getMapper(PaymentMethodMapper.class);
    }

    public void insertPaymentMethod(PaymentMethod paymentMethod) {
        try {
            if (paymentMethodMapper.getPaymentMethodById(paymentMethod.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            paymentMethodMapper.insertPaymentMethod(paymentMethod);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        try {
            if (paymentMethodMapper.getPaymentMethodById(paymentMethod.getId()) == null) {
                logger.error("Updation Unsuccessful! for paymentMethod Id:" + paymentMethod.getId());
                throw new RecordNotFoundException(paymentMethod.getId(), "PaymentMethod");
            }
            logger.info("Updation complete for paymentMethod Id:" + paymentMethod.getId());
            paymentMethodMapper.updatePaymentMethod(paymentMethod);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public void deletePaymentMethod(int paymentMethodId) {
        try {
            if (paymentMethodMapper.getPaymentMethodById(paymentMethodId) == null) {
                logger.error("Deletion Unsuccessful for paymentMethodId:" + paymentMethodId);
                throw new RecordNotFoundException(paymentMethodId, "PaymentMethod");
            }
            paymentMethodMapper.deletePaymentMethod(paymentMethodId);
            logger.info("Deletion complete for " + paymentMethodId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }  catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public PaymentMethod getPaymentMethodById(int paymentMethodId) {
        try {
            if (paymentMethodMapper.getPaymentMethodById(paymentMethodId) == null) {
                throw new RecordNotFoundException(paymentMethodId, "PaymentMethod");
            }
            PaymentMethod paymentMethod = paymentMethodMapper.getPaymentMethodById(paymentMethodId);
            logger.info(paymentMethod);
            return paymentMethod;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        try {
            logger.info(paymentMethodMapper.getAllPaymentMethods());
            return paymentMethodMapper.getAllPaymentMethods();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
