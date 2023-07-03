package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.VehicleFeature;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.VehicleFeatureMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisVehicleFeatureService implements VehicleFeatureMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisVehicleFeatureService.class);
    private final VehicleFeatureMapper vehicleFeatureMapper;
    private SqlSession sqlSession;

    public MyBatisVehicleFeatureService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.vehicleFeatureMapper = sqlSession.getMapper(VehicleFeatureMapper.class);
    }

    public void insertVehicleFeature(VehicleFeature vehicleFeature) {
        try {
            if (vehicleFeatureMapper.getVehicleFeatureById(vehicleFeature.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            vehicleFeatureMapper.insertVehicleFeature(vehicleFeature);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateVehicleFeature(VehicleFeature vehicleFeature) {
        try {
            if (vehicleFeatureMapper.getVehicleFeatureById(vehicleFeature.getId()) == null) {
                logger.error("Updation Unsuccessful! for vehicle feature Id: " + vehicleFeature.getId());
                throw new RecordNotFoundException(vehicleFeature.getId(), "Vehicle Feature");
            }
            vehicleFeatureMapper.updateVehicleFeature(vehicleFeature);
            logger.info("Updation complete for vehicle feature Id: " + vehicleFeature.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteVehicleFeature(int featureId) {
        try {
            if (vehicleFeatureMapper.getVehicleFeatureById(featureId) == null) {
                logger.error("Deletion Unsuccessful for featureId: " + featureId);
                throw new RecordNotFoundException(featureId, "Vehicle Feature");
            }
            logger.info("Deletion complete for " + featureId);
            vehicleFeatureMapper.deleteVehicleFeature(featureId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public VehicleFeature getVehicleFeatureById(int featureId) {
        try {
            if (vehicleFeatureMapper.getVehicleFeatureById(featureId) == null) {
                throw new RecordNotFoundException(featureId, "Vehicle Feature");
            }
            VehicleFeature vehicleFeature = vehicleFeatureMapper.getVehicleFeatureById(featureId);
            logger.info(vehicleFeature);
            return vehicleFeature;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<VehicleFeature> getAllVehicleFeatures() {
        logger.info(vehicleFeatureMapper.getAllVehicleFeatures());
        return vehicleFeatureMapper.getAllVehicleFeatures();
    }
}
