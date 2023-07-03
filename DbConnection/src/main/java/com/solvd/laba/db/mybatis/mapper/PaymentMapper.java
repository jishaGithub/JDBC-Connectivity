package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.Payment;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface PaymentMapper {
    @Insert("INSERT INTO payment(payment_date, total_amount, customer_id, payment_method_id) VALUES " +
            "(#{paymentDate}, #{totalAmount}, #{customerId}, #{paymentMethodId})")
    void insertPayment(Payment payment);

    @Update("UPDATE payment SET payment_date = #{paymentDate}, total_amount = #{totalAmount}, " +
            "customer_id = #{customerId}, payment_method_id = #{paymentMethodId} WHERE id = #{id}")
    void updatePayment(Payment payment);

    @Delete("DELETE FROM payment WHERE id = #{id}")
    void deletePayment(int paymentId);

    @Select("SELECT * FROM payment WHERE id = #{id}")
    @Results(id = "selectByIdResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "paymentDate", column = "payment_date"),
            @Result(property = "totalAmount", column = "total_amount"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "paymentMethodId", column = "payment_method_id")
    })
    Payment getPaymentById(int paymentId);

    @Select("SELECT * FROM payment")
    @Results(id = "selectAllResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "paymentDate", column = "payment_date"),
            @Result(property = "totalAmount", column = "total_amount"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "paymentMethodId", column = "payment_method_id")
    })
    List<Payment> getAllPayments();
}
