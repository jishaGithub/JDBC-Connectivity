package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.VehicleHasVehicleFeature;
import com.solvd.laba.db.mybatis.mapper.VehicleHasVehicleFeatureMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MyBatisVehicleHasVehicleFeatureService implements VehicleHasVehicleFeatureMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisVehicleHasVehicleFeatureService.class);
    private final VehicleHasVehicleFeatureMapper vehicleHasVehicleFeatureMapper;
    private SqlSession sqlSession;

    public MyBatisVehicleHasVehicleFeatureService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.vehicleHasVehicleFeatureMapper = sqlSession.getMapper(VehicleHasVehicleFeatureMapper.class);
    }

    public List<VehicleHasVehicleFeature> getAllVehicleHasVehicleFeatures() {
        logger.info(vehicleHasVehicleFeatureMapper.getAllVehicleHasVehicleFeatures());
        return vehicleHasVehicleFeatureMapper.getAllVehicleHasVehicleFeatures();
    }
}
