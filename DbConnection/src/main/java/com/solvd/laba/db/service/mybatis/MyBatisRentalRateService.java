package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.RentalRate;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.RentalRateMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyBatisRentalRateService implements RentalRateMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisRentalRateService.class);
    private final RentalRateMapper rentalRateMapper;
    private SqlSession sqlSession;

    public MyBatisRentalRateService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.rentalRateMapper = sqlSession.getMapper(RentalRateMapper.class);
    }

    public void insertRentalRate(RentalRate rentalRate) {
        try {
            if (rentalRateMapper.getRentalRateById(rentalRate.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            rentalRateMapper.insertRentalRate(rentalRate);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateRentalRate(RentalRate rentalRate) {
        try {
            if (rentalRateMapper.getRentalRateById(rentalRate.getId()) == null) {
                logger.error("Updation Unsuccessful! for rental rate Id:" + rentalRate.getId());
                throw new RecordNotFoundException(rentalRate.getId(), "RentalRate");
            }
            rentalRateMapper.updateRentalRate(rentalRate);
            logger.info("Updation complete for rental rate Id:" + rentalRate.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteRentalRate(int rentalRateId) {
        try {
            if (rentalRateMapper.getRentalRateById(rentalRateId) == null) {
                logger.error("Deletion Unsuccessful for rental rate Id:" + rentalRateId);
                throw new RecordNotFoundException(rentalRateId, "RentalRate");
            }
            logger.info("Deletion complete for " + rentalRateId);
            rentalRateMapper.deleteRentalRate(rentalRateId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public RentalRate getRentalRateById(int rentalRateId) {
        try {
            if (rentalRateMapper.getRentalRateById(rentalRateId) == null) {
                throw new RecordNotFoundException(rentalRateId, "RentalRate");
            }
            RentalRate rentalRate = rentalRateMapper.getRentalRateById(rentalRateId);
            logger.info(rentalRate);
            return rentalRate;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<RentalRate> getAllRentalRates() {
        logger.info(rentalRateMapper.getAllRentalRates());
        return rentalRateMapper.getAllRentalRates();
    }
}
