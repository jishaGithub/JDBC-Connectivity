package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.Vehicle;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.VehicleMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisVehicleService implements VehicleMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisVehicleService.class);
    private final VehicleMapper vehicleMapper;
    private SqlSession sqlSession = null;

    public MyBatisVehicleService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.vehicleMapper = sqlSession.getMapper(VehicleMapper.class);
    }

    public void insertVehicle(Vehicle vehicle) {
        try {
            if (vehicleMapper.getVehicleById(vehicle.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            vehicleMapper.insertVehicle(vehicle);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        try {
            if (vehicleMapper.getVehicleById(vehicle.getId()) == null) {
                logger.error("Updation Unsuccessful! Vehicle with ID " + vehicle.getId() + " not found.");
                throw new RecordNotFoundException(vehicle.getId(), "Vehicle");
            }
            vehicleMapper.updateVehicle(vehicle);
            logger.info("Updation complete for vehicle ID: " + vehicle.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error("category_id / customer_id not valid."+e.getMessage());
        }
    }

    public void deleteVehicle(int vehicleId) {
        try {
            if (vehicleMapper.getVehicleById(vehicleId) == null) {
                logger.error("Deletion Unsuccessful for vehicle ID: " + vehicleId);
                throw new RecordNotFoundException(vehicleId, "Vehicle");
            }
            logger.info("Deletion complete for vehicle ID: " + vehicleId);
            vehicleMapper.deleteVehicle(vehicleId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Vehicle getVehicleById(int vehicleId) {
        try {
            if (vehicleMapper.getVehicleById(vehicleId) == null) {
                throw new RecordNotFoundException(vehicleId, "Vehicle");
            }
            Vehicle vehicle = vehicleMapper.getVehicleById(vehicleId);
            logger.info(vehicle);
            return vehicle;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Vehicle> getAllVehicles() {
        logger.info(vehicleMapper.getAllVehicles());
        return vehicleMapper.getAllVehicles();
    }
}
