package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.Insurance;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.InsuranceMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisInsuranceService implements InsuranceMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisInsuranceService.class);
    private final InsuranceMapper insuranceMapper;
    private SqlSession sqlSession = null;

    public MyBatisInsuranceService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.insuranceMapper = sqlSession.getMapper(InsuranceMapper.class);
    }

    public void insertInsurance(Insurance insurance) {
        try {
            if (insuranceMapper.getInsuranceById(insurance.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            insuranceMapper.insertInsurance(insurance);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateInsurance(Insurance insurance) {
        try {
            if (insuranceMapper.getInsuranceById(insurance.getId()) == null) {
                logger.error("Updation Unsuccessful! for insurance Id: " + insurance.getId());
                throw new RecordNotFoundException(insurance.getId(), "Insurance");
            }
            insuranceMapper.updateInsurance(insurance);
            logger.info("Updation complete for insurance Id: " + insurance.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("customer_id is not valid."+e.getMessage());
        }
    }

    public void deleteInsurance(int insuranceId) {
        try {
            if (insuranceMapper.getInsuranceById(insuranceId) == null) {
                logger.error("Deletion Unsuccessful for insuranceId: " + insuranceId);
                throw new RecordNotFoundException(insuranceId, "Insurance");
            }
            logger.info("Deletion complete for " + insuranceId);
            insuranceMapper.deleteInsurance(insuranceId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Insurance getInsuranceById(int insuranceId) {
        try {
            if (insuranceMapper.getInsuranceById(insuranceId) == null) {
                throw new RecordNotFoundException(insuranceId, "Insurance");
            }
            Insurance insurance = insuranceMapper.getInsuranceById(insuranceId);
            logger.info(insurance);
            return insurance;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Insurance> getAllInsurances() {
        logger.info(insuranceMapper.getAllInsurances());
        return insuranceMapper.getAllInsurances();
    }
}
