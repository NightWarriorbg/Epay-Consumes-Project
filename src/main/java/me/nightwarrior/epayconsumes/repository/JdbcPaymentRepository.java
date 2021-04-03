package me.nightwarrior.epayconsumes.repository;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class JdbcPaymentRepository implements PaymentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM payments", Integer.class);
    }

    @Override
    public int save(Payment payment) {
        log.info("Saving " + payment.toString() + " into the database.");

        return jdbcTemplate.update(
                "INSERT INTO payments (payment_id, user_id, merchant_id, merchant_ref_no, subscription_id, due_id, payment_date, amount, total_amount," +
                        "fee, short_desc, long_desc, payment_method, payment_account, transaction_id, dt, epay_status, epay_err_code, epay_err_desc, " +
                        "accounting_status, accounting_reference) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
                payment.getPaymentId(), payment.getUserId(), payment.getMerchantId(), payment.getMerchantRefNo(), payment.getSubscriptionId(), payment.getDueId(), payment.getPaymentDate(),
                payment.getAmount(), payment.getTotalAmount(), payment.getFee(), payment.getShortDesc(), payment.getLongDesc(), payment.getPaymentMethod(),
                payment.getPaymentAccount(), payment.getTransactionId(), payment.getDt(), payment.getEpayStatus(), payment.getEpayErrCode(), payment.getEpayErrDesc(),
                payment.getAccountingStatus(), payment.getAccountingReference()
        );
    }

    @Override
    public int update(Payment payment) {
        log.info("Updating " + payment.toString() + " in the database.");


        return jdbcTemplate.update(
                "UPDATE payments SET user_id = ?, merchant_id = ?, merchant_ref_no = ?, subscription_id = ?, due_id = ?, payment_date = ?" +
                        "amount = ?, total_amount = ?, fee = ?, short_desc = ?, long_desc = ?, payment_method = ?, payment_account = ?, transaction_id = ?" +
                        "dt = ?, epay_status = ?, epay_err_code = ?, epay_err_desc = ?, accounting_status = ?, accouning_reference = ? WHERE payment_id = ?",
                payment.getUserId(), payment.getMerchantId(), payment.getMerchantRefNo(), payment.getSubscriptionId(), payment.getDueId(), payment.getPaymentDate(),
                payment.getAmount(), payment.getTotalAmount(), payment.getFee(), payment.getShortDesc(), payment.getLongDesc(), payment.getPaymentMethod(),
                payment.getPaymentAccount(), payment.getTransactionId(), payment.getDt(), payment.getEpayStatus(), payment.getEpayErrCode(), payment.getEpayErrDesc(),
                payment.getAccountingStatus(), payment.getAccountingReference(), payment.getPaymentId()
        );
    }

    @Override
    public int deleteById(Long id) {
        log.info("Deleting a payment with id " + id + " from the database.");


        return jdbcTemplate.update("DELETE payments WHERE payment_id = ?", id);
    }

    @Override
    public void empty() {
        log.info("Deleting all records from the payments table.");

        jdbcTemplate.update("DELETE FROM payments");
    }

    @Override
    public List<Payment> findAll() {
        log.info("Getting all records from the payments table.");


        return jdbcTemplate.query(
                "SELECT * FROM payments",this::mapRowToPayment);
    }

    @Override
    public List<Payment> findById(Long id) {
        log.info("Getting the payment with id" + id +  " from the database.");


        return jdbcTemplate.query(
                "SELECT * FROM payments WHERE payment_id = ?", this::mapRowToPayment, id);
    }

    @Override
    public Payment mapRowToPayment(ResultSet rs, int rowNum) throws SQLException {
        return new Payment(
            rs.getInt("payment_id"),
            rs.getInt("user_id"),
            rs.getInt("merchant_id"),
            rs.getString("merchant_ref_no"),
            rs.getString("subscription_id"),
            rs.getInt("due_id"),
            rs.getDate("payment_date"),
            rs.getInt("amount"),
            rs.getInt("total_amount"),
            rs.getInt("fee"),
            rs.getString("short_desc"),
            rs.getString("long_desc"),
            rs.getString("payment_method"),
            rs.getString("payment_account"),
            rs.getString("transaction_id"),
            rs.getString("dt"),
            rs.getString("epay_status"),
            rs.getString("epay_err_code"),
            rs.getString("epay_err_desc"),
            rs.getString("accounting_status"),
            rs.getString("accaunting_reference")
        );
    }
}
