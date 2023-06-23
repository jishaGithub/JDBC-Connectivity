package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.RentalRate;

import java.util.List;

public interface RentalRateMapper {
    void insertRentalRate(RentalRate rentalRate);

    void updateRentalRate(RentalRate rentalRate);

    void deleteRentalRate(int rentalRateId);

    RentalRate getRentalRateById(int rentalRateId);

    List<RentalRate> getAllRentalRates();
}
