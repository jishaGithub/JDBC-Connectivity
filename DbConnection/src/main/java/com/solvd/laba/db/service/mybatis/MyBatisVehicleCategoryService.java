package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.VehicleCategory;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.VehicleCategoryMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisVehicleCategoryService implements VehicleCategoryMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisVehicleCategoryService.class);
    private final VehicleCategoryMapper vehicleCategoryMapper;
    private SqlSession sqlSession;

    public MyBatisVehicleCategoryService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.vehicleCategoryMapper = sqlSession.getMapper(VehicleCategoryMapper.class);
    }

    public void insertVehicleCategory(VehicleCategory vehicleCategory) {
        try {
            if (vehicleCategoryMapper.getVehicleCategoryById(vehicleCategory.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            vehicleCategoryMapper.insertVehicleCategory(vehicleCategory);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateVehicleCategory(VehicleCategory vehicleCategory) {
        try {
            if (vehicleCategoryMapper.getVehicleCategoryById(vehicleCategory.getId()) == null) {
                logger.error("Updation Unsuccessful! for vehicle category Id: " + vehicleCategory.getId());
                throw new RecordNotFoundException(vehicleCategory.getId(), "Vehicle Category");
            }
            vehicleCategoryMapper.updateVehicleCategory(vehicleCategory);
            logger.info("Updation complete for vehicle category Id: " + vehicleCategory.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteVehicleCategory(int categoryId) {
        try {
            if (vehicleCategoryMapper.getVehicleCategoryById(categoryId) == null) {
                logger.error("Deletion Unsuccessful for categoryId: " + categoryId);
                throw new RecordNotFoundException(categoryId, "Vehicle Category");
            }
            logger.info("Deletion complete for " + categoryId);
            vehicleCategoryMapper.deleteVehicleCategory(categoryId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public VehicleCategory getVehicleCategoryById(int categoryId) {
        try {
            if (vehicleCategoryMapper.getVehicleCategoryById(categoryId) == null) {
                throw new RecordNotFoundException(categoryId, "Vehicle Category");
            }
            VehicleCategory vehicleCategory = vehicleCategoryMapper.getVehicleCategoryById(categoryId);
            logger.info(vehicleCategory);
            return vehicleCategory;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<VehicleCategory> getAllVehicleCategories() {
        logger.info(vehicleCategoryMapper.getAllVehicleCategories());
        return vehicleCategoryMapper.getAllVehicleCategories();
    }
}
