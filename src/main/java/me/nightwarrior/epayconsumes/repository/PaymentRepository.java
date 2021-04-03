package me.nightwarrior.epayconsumes.repository;

import me.nightwarrior.epayconsumes.model.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PaymentRepository {

    int count();

    int save(Payment payment);

    int update(Payment payment);

    int deleteById(Long id);

    void empty();

    List<Payment> findAll();

    List<Payment> findById(Long id);

    Payment mapRowToPayment(ResultSet resultSet, int rowNumn) throws SQLException;

}
