package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.CustomerAuthentication;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CustomerAuthenticationMapper {
    @Insert("INSERT INTO customer_authentication(user_name, password, customer_id) VALUES " +
            "(#{userName}, #{password}, #{customerId})")
    void insertCustomerAuthentication(CustomerAuthentication customerAuthentication);

    @Update("UPDATE customer_authentication SET user_name = #{userName}, " +
            "password = #{password}, customer_id = #{customerId} WHERE id = #{id}")
    void updateCustomerAuthentication(CustomerAuthentication customerAuthentication);

    @Delete("DELETE FROM customer_authentication WHERE id = #{id}")
    void deleteCustomerAuthentication(int customerAuthenticationId);

    @Select("SELECT * FROM customer_authentication WHERE id = #{id}")
    @Results(id = "selectByIdResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "customerId", column = "customer_id")
    })
    CustomerAuthentication getCustomerAuthenticationById(int customerAuthenticationId);

    @Select("SELECT * FROM customer_authentication")
    @Results(id = "selectAllResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "customerId", column = "customer_id")
    })
    List<CustomerAuthentication> getAllCustomerAuthentications();
}
