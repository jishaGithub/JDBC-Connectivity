package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.Branch;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.BranchMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisBranchService implements BranchMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisBranchService.class);
    private final BranchMapper branchMapper;
    private SqlSession sqlSession = null;

    public MyBatisBranchService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.branchMapper = sqlSession.getMapper(BranchMapper.class);
    }

    public void insertBranch(Branch branch) {
        try {
            if (branchMapper.getBranchById(branch.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            branchMapper.insertBranch(branch);
            logger.info("Insertion complete");
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateBranch(Branch branch) {
        try {
            if (branchMapper.getBranchById(branch.getId()) == null) {
                logger.error("Updation Unsuccessful! for branch Id: " + branch.getId());
                throw new RecordNotFoundException(branch.getId(), "Branch");
            }
            branchMapper.updateBranch(branch);
            logger.info("Updation complete for branch Id: " + branch.getId());
        } catch(RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("branch_location_id is not valid" + e.getMessage());
        }
    }

    public void deleteBranch(int branchId) {
        try {
            if (branchMapper.getBranchById(branchId) == null) {
                logger.error("Deletion Unsuccessful for branch Id: " + branchId);
                throw new RecordNotFoundException(branchId, "Branch");
            }
            logger.info("Deletion complete for branch Id: " + branchId);
            branchMapper.deleteBranch(branchId);
        } catch(RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Branch getBranchById(int branchId) {
        try {
            if (branchMapper.getBranchById(branchId) == null) {
                throw new RecordNotFoundException(branchId, "Branch");
            }
            Branch branch = branchMapper.getBranchById(branchId);
            logger.info(branch);
            return branch;
        } catch(RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Branch> getAllBranches() {
        logger.info(branchMapper.getAllBranches());
        return branchMapper.getAllBranches();
    }
}
