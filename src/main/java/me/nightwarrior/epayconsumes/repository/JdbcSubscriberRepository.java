package me.nightwarrior.epayconsumes.repository;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Payment;
import me.nightwarrior.epayconsumes.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class JdbcSubscriberRepository implements SubscriptionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM subscribers", Integer.class);
    }

    @Override
    public int save(Subscription subscription) {
        log.info("Saving " + subscription.toString() + " into the database.");

        return jdbcTemplate.update(
                "INSERT INTO subscriptions (user_id, alias, merchant_ref_no, merchant_id, customer_name, address, short_description, payment_method," +
                        "payment_account, automatic_payment, automatic_max_amount, automatic_max_days_retry, insert_date, last_update_date, cached_due_amount," +
                        "cached_due_checked_date, cached_due_service_response, cached_due_error_code, cached_due_error_description, cached_due_id) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                subscription.getUserId(), subscription.getAlias(), subscription.getMerchantRefNumber(), subscription.getMerchantId(), subscription.getCustomerName(),
                subscription.getAddress(), subscription.getShortDescription(), subscription.getPaymentMethod(), subscription.getPaymentAccount(), subscription.getAutomaticPayment(),
                subscription.getAutomaticMaxAmount(), subscription.getAutomaticMaxDaysRetry(), subscription.getInsertDate(), subscription.getLastUpdateDate(),
                subscription.getCachedDueAmount(), subscription.getCachedDueCheckedDate(), subscription.getCachedDueServiceResponse(), subscription.getCachedDueErrorCode(),
                subscription.getCachedDueErrorDescription(), subscription.getCachedDueId()
        );
    }

    @Override
    public int update(Subscription subscription, Long id) {
        log.info("Updating " + subscription.toString() + " in the database.");

        return jdbcTemplate.update(
                "UPDATE subscriptions SET user_id = ?, alias = ?, merchant_ref_no = ?, merchant_id = ?, customer_name = ?, address = ?" +
                        "short_description = ?, payment_method = ?, payment_account = ?, automatic_payment = ?, automatic_max_amount = ?, automatic_max_days_retry = ?" +
                        ", insert_date = ?, last_update_date = ?, cached_due_amount = ?, cached_due_checked_date = ?, cached_due_service_response = ?, " +
                        "cached_due_error_code = ?, cached_due_error_description = ?, cached_due_id = ? WHERE subscription_id = ?",
                subscription.getUserId(), subscription.getAlias(), subscription.getMerchantRefNumber(), subscription.getMerchantId(), subscription.getCustomerName(),
                subscription.getAddress(), subscription.getShortDescription(), subscription.getPaymentMethod(), subscription.getPaymentAccount(), subscription.getAutomaticPayment(),
                subscription.getAutomaticMaxAmount(), subscription.getAutomaticMaxDaysRetry(), subscription.getInsertDate(), subscription.getLastUpdateDate(),
                subscription.getCachedDueAmount(), subscription.getCachedDueCheckedDate(), subscription.getCachedDueServiceResponse(), subscription.getCachedDueErrorCode(),
                subscription.getCachedDueErrorDescription(), subscription.getCachedDueId(), id
        );
    }

    @Override
    public int deleteById(Long id) {
        log.info("Deleting a subscription with id " + id + " from the database.");


        return jdbcTemplate.update("DELETE subscriptions WHERE sunscription_id = ?", id);
    }

    @Override
    public void empty() {
        log.info("Deleting all records from the subscriptions table.");

        jdbcTemplate.update("DELETE FROM subscriptions");
    }

    @Override
    public List<Subscription> findAll() {
        log.info("Getting all records from the subscriptions table.");


        return jdbcTemplate.query(
                "SELECT * FROM subscriptions",this::mapRowToSubscriber);
    }

    @Override
    public Subscription findById(Long id) {
        log.info("Getting the payment with id" + id +  " from the database.");


        return (Subscription) jdbcTemplate.query(
                "SELECT * FROM subscriptions WHERE subscription_id = ?", this::mapRowToSubscriber, id);
    }

    @Override
    public Subscription mapRowToSubscriber(ResultSet rs, int rowNum) throws SQLException {
        return new Subscription(
                rs.getInt("user_id"),
                rs.getString("alias"),
                rs.getString("merchant_ref_no"),
                rs.getString("merchant_id"),
                rs.getString("customer_name"),
                rs.getString("address"),
                rs.getString("short_description"),
                rs.getString("payment_method"),
                rs.getString("payment_account"),
                rs.getString("automatic_payment"),
                rs.getInt("automatic_max_amount"),
                rs.getString("automatic_max_days_retry"),
                rs.getDate("insert-date"),
                rs.getDate("last_update_date"),
                rs.getInt("cached_due_amount"),
                rs.getDate("cached_due_checked_date"),
                rs.getString("cached_due_service_response"),
                rs.getString("cached_due_error_code"),
                rs.getString("cached_due_error_description"),
                rs.getInt("cached_due_id")
        );
    }
}
