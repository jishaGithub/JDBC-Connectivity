package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.Rental;

import java.util.List;

public interface RentalMapper {
    void insertRental(Rental rental);

    void updateRental(Rental rental);

    void deleteRental(int rentalId);

    Rental getRentalById(int rentalId);

    List<Rental> getAllRentals();
}
