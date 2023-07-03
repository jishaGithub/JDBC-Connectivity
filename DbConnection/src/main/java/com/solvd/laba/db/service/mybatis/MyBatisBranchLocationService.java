package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.BranchLocation;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.BranchLocationMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyBatisBranchLocationService implements BranchLocationMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisBranchLocationService.class);
    private final BranchLocationMapper branchLocationMapper;
    private SqlSession sqlSession = null;

    public MyBatisBranchLocationService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.branchLocationMapper = sqlSession.getMapper(BranchLocationMapper.class);
    }

    public void insertBranchLocation(BranchLocation branchLocation) {
        try {
            if (branchLocationMapper.getBranchLocationById(branchLocation.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            branchLocationMapper.insertBranchLocation(branchLocation);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateBranchLocation(BranchLocation branchLocation) {
        try {
            if (branchLocationMapper.getBranchLocationById(branchLocation.getId()) == null) {
                logger.error("Updation Unsuccessful! for branchLocation Id: " + branchLocation.getId());
                throw new RecordNotFoundException(branchLocation.getId(), "BranchLocation");
            }
            branchLocationMapper.updateBranchLocation(branchLocation);
            logger.info("Updation complete for branchLocation Id: " + branchLocation.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteBranchLocation(int branchLocationId) {
        try {
            if (branchLocationMapper.getBranchLocationById(branchLocationId) == null) {
                logger.error("Deletion Unsuccessful for branchLocationId: " + branchLocationId);
                throw new RecordNotFoundException(branchLocationId, "BranchLocation");
            }
            logger.info("Deletion complete for " + branchLocationId);
            branchLocationMapper.deleteBranchLocation(branchLocationId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public BranchLocation getBranchLocationById(int branchLocationId) {
        try {
            if (branchLocationMapper.getBranchLocationById(branchLocationId) == null) {
                throw new RecordNotFoundException(branchLocationId, "BranchLocation");
            }
            BranchLocation branchLocation = branchLocationMapper.getBranchLocationById(branchLocationId);
            logger.info(branchLocation);
            return branchLocation;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<BranchLocation> getAllBranchLocations() {
        logger.info(branchLocationMapper.getAllBranchLocations());
        return branchLocationMapper.getAllBranchLocations();
    }
}
