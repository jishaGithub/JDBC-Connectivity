package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.Insurance;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface InsuranceMapper {
    @Insert("INSERT INTO insurance(insurance_name, coverage, customer_id) VALUES " +
            "(#{insuranceName}, #{coverage}, #{customerId})")
    void insertInsurance(Insurance insurance);

    @Update("UPDATE insurance SET insurance_name = #{insuranceName}, coverage = #{coverage}, " +
            "customer_id = #{customerId} WHERE id = #{id}")
    void updateInsurance(Insurance insurance);

    @Delete("DELETE FROM insurance WHERE id = #{id}")
    void deleteInsurance(int insuranceId);

    @Select("SELECT * FROM insurance WHERE id = #{id}")
    @Results(id = "selectByIdResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "insuranceName", column = "insurance_name"),
            @Result(property = "coverage", column = "coverage"),
            @Result(property = "customerId", column = "customer_id")
    })
    Insurance getInsuranceById(int insuranceId);

    @Select("SELECT * FROM insurance")
    @Results(id = "selectAllResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "insuranceName", column = "insurance_name"),
            @Result(property = "coverage", column = "coverage"),
            @Result(property = "customerId", column = "customer_id")
    })
    List<Insurance> getAllInsurances();
}
