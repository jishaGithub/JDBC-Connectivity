package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.BranchLocation;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface BranchLocationMapper {
    @Insert("INSERT INTO branch_location(street, city, state, zip) VALUES " +
            "(#{street}, #{city}, #{state}, #{zip})")
    void insertBranchLocation(BranchLocation branchLocation);

    @Update("UPDATE branch_location SET street = #{street}, " +
            "city = #{city}, state = #{state}, zip = #{zip} WHERE id = #{id}")
    void updateBranchLocation(BranchLocation branchLocation);

    @Delete("DELETE FROM branch_location WHERE id = #{id}")
    void deleteBranchLocation(int branchLocationId);

    @Select("SELECT * FROM branch_location WHERE id = #{id}")
    @Results(id = "selectByIdResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "street", column = "street"),
            @Result(property = "city", column = "city"),
            @Result(property = "state", column = "state"),
            @Result(property = "zip", column = "zip")
    })
    BranchLocation getBranchLocationById(int branchLocationId);

    @Select("SELECT * FROM branch_location")
    @Results(id = "selectAllResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "street", column = "street"),
            @Result(property = "city", column = "city"),
            @Result(property = "state", column = "state"),
            @Result(property = "zip", column = "zip")
    })
    List<BranchLocation> getAllBranchLocations();
}
