package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.Payment;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.PaymentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisPaymentService implements PaymentMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisPaymentService.class);
    private final PaymentMapper paymentMapper;
    private SqlSession sqlSession = null;

    public MyBatisPaymentService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.paymentMapper = sqlSession.getMapper(PaymentMapper.class);
    }

    public void insertPayment(Payment payment) {
        try {
            if (paymentMapper.getPaymentById(payment.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            paymentMapper.insertPayment(payment);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updatePayment(Payment payment) {
        try {
            if (paymentMapper.getPaymentById(payment.getId()) == null) {
                logger.error("Updation Unsuccessful! for payment Id: " + payment.getId());
                throw new RecordNotFoundException(payment.getId(), "Payment");
            }
            logger.info("Updation complete for payment Id: " + payment.getId());
            paymentMapper.updatePayment(payment);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("customer_id / payment_method_id not valid"+e.getMessage());
        }
    }

    public void deletePayment(int paymentId) {
        try {
            if (paymentMapper.getPaymentById(paymentId) == null) {
                logger.error("Deletion Unsuccessful for paymentId: " + paymentId);
                throw new RecordNotFoundException(paymentId, "Payment");
            }
            logger.info("Deletion complete for " + paymentId);
            paymentMapper.deletePayment(paymentId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Payment getPaymentById(int paymentId) {
        try {
            if (paymentMapper.getPaymentById(paymentId) == null) {
                throw new RecordNotFoundException(paymentId, "Payment");
            }
            Payment payment = paymentMapper.getPaymentById(paymentId);
            logger.info(payment);
            return payment;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        logger.info(paymentMapper.getAllPayments());
        return paymentMapper.getAllPayments();
    }
}
