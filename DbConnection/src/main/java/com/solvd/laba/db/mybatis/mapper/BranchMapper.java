package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.Branch;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface BranchMapper {
    @Insert("INSERT INTO branch(branch_name, branch_location_id) VALUES " +
            "(#{branchName}, #{branchLocationId})")
    void insertBranch(Branch branch);

    @Update("UPDATE branch SET branch_name = #{branchName}, " +
            "branch_location_id = #{branchLocationId} WHERE id = #{id}")
    void updateBranch(Branch branch);

    @Delete("DELETE FROM branch WHERE id = #{id}")
    void deleteBranch(int branchId);

    @Select("SELECT * FROM branch WHERE id = #{id}")
    @Results(id = "selectByIdResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "branchName", column = "branch_name"),
            @Result(property = "branchLocationId", column = "branch_location_id")
    })
    Branch getBranchById(int branchId);

    @Select("SELECT * FROM branch")
    @Results(id = "selectAllResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "branchName", column = "branch_name"),
            @Result(property = "branchLocationId", column = "branch_location_id")
    })
    List<Branch> getAllBranches();
}
