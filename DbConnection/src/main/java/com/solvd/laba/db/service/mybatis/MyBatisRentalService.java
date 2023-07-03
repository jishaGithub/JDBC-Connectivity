package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.Rental;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.RentalMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyBatisRentalService implements RentalMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisRentalService.class);
    private final RentalMapper rentalMapper;
    private SqlSession sqlSession;

    public MyBatisRentalService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.rentalMapper = sqlSession.getMapper(RentalMapper.class);
    }

    public void insertRental(Rental rental) {
        try {
            if (rentalMapper.getRentalById(rental.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            rentalMapper.insertRental(rental);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateRental(Rental rental) {
        try {
            if (rentalMapper.getRentalById(rental.getId()) == null) {
                logger.error("Updation Unsuccessful! for rental Id:" + rental.getId());
                throw new RecordNotFoundException(rental.getId(), "Rental");
            }
            rentalMapper.updateRental(rental);
            logger.info("Updation complete for rental Id:" + rental.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteRental(int rentalId) {
        try {
            if (rentalMapper.getRentalById(rentalId) == null) {
                logger.error("Deletion Unsuccessful for rentalId:" + rentalId);
                throw new RecordNotFoundException(rentalId, "Rental");
            }
            logger.info("Deletion complete for " + rentalId);
            rentalMapper.deleteRental(rentalId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Rental getRentalById(int rentalId) {
        try {
            if (rentalMapper.getRentalById(rentalId) == null) {
                throw new RecordNotFoundException(rentalId, "Rental");
            }
            Rental rental = rentalMapper.getRentalById(rentalId);
            logger.info(rental);
            return rental;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Rental> getAllRentals() {
        logger.info(rentalMapper.getAllRentals());
        return rentalMapper.getAllRentals();
    }
}
