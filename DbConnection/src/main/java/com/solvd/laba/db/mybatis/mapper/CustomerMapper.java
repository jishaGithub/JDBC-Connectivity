package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.Customer;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CustomerMapper {
    @Insert("INSERT INTO customer(first_name, last_name, phone_number, email, age) VALUES "+
    "(#{firstName}, #{lastName}, #{phoneNumber}, #{email}, #{age})")
    void insertCustomer(Customer customer);

    @Update("UPDATE customer SET first_name = #{firstName}, last_name = #{lastName}, " +
            "email = #{email}, phone_number = #{phoneNumber}, age = #{age} WHERE id = #{id}")
    void updateCustomer(Customer customer);

    @Delete("DELETE FROM customer WHERE id = #{id}")
    void deleteCustomer(int customerId);

    @Select("SELECT * FROM customer WHERE id = #{id}")
    @Results(id = "selectByIdResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "email", column = "email"),
            @Result(property = "age", column = "age")
    })
    Customer getCustomerById(int customerId);

    @Select("SELECT * FROM customer")
    @Results(id = "selectAllResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "email", column = "email"),
            @Result(property = "age", column = "age")
    })
    List<Customer> getAllCustomers();
}
