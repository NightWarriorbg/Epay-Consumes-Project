package me.nightwarrior.epayconsumes.repository;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Due;
import me.nightwarrior.epayconsumes.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class JdbcDueRepository implements DueRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM dues", Integer.class);
    }

    @Override
    public int save(Due due) {
        log.info("Saving " + due.toString() + " into the database.");

        return jdbcTemplate.update(
                "INSERT INTO dues (user_id, merchant_id, merchant_ref_no, subscription_id, due_date, amount, total_amount," +
                        "fee, short_desc, long_desc, second_id, due_status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
                due.getUserId(), due.getMerchantId(), due.getMerchantRefNo(), due.getSubscriptionId(), due.getDueDate(), due.getAmount(),
                due.getTotalAmount(), due.getFee(), due.getShortDesc(), due.getLongDesc(), due.getSecondId(), due.getDueStatus()
        );
    }

    @Override
    public int update(Due due) {
        log.info("Updating " + due.toString() + " in the database.");


        return jdbcTemplate.update(
                "UPDATE dues SET user_id = ?, merchant_id = ?, merchant_ref_no = ?, subscription_id = ?, due_date = ?, amount = ?," +
                        "total_amount = ?, fee = ?, short_desc = ?, long_desc = ?, second_id = ?, due_status = ? WHERE due_id = ?",
                due.getUserId(), due.getMerchantId(), due.getMerchantRefNo(), due.getSubscriptionId(), due.getDueDate(), due.getAmount(),
                due.getTotalAmount(), due.getFee(), due.getShortDesc(), due.getLongDesc(), due.getSecondId(), due.getDueStatus(), due.getDueId()
        );
    }

    @Override
    public int deleteById(Long id) {
        log.info("Deleting a due with id " + id + " from the database.");


        return jdbcTemplate.update(
                "DELETE dues WHERE due_id = ?",
                id);
    }

    @Override
    public void empty() {
        log.info("Deleting all records from the dues table.");

        jdbcTemplate.update("DELETE FROM dues");
    }

    @Override
    public boolean isEmpty() {
        String query = "SELECT count(*) FROM dues";
        return jdbcTemplate.query(query, (ResultSet rs) -> {
            if (rs.next()) {
                return true;
            }
            return false;
        });
    }

    @Override
    public List<Due> findAll() {
        log.info("Getting all records from the dues table.");


        return jdbcTemplate.query(
                "SELECT * FROM dues",this::mapRowToDue);
    }

    @Override
    public List<Due> findById(Long id) {
        log.info("Getting the merchant with id" + id +  " from the database.");


        return jdbcTemplate.query(
                "SELECT * FROM dues WHERE due_id = ?", this::mapRowToDue, id);
    }

    @Override
    public Due mapRowToDue(ResultSet rs, int rowNum) throws SQLException {
        return new Due(
                rs.getInt("user_id"),
                rs.getInt("merchant_id"),
                rs.getString("merchant_ref_no"),
                rs.getInt("subscription_id"),
                rs.getDate("due_date"),
                rs.getInt("amount"),
                rs.getInt("total_amount"),
                rs.getInt("fee"),
                rs.getString("short_desc"),
                rs.getString("long_desc"),
                rs.getString("second_id"),
                rs.getString("due_status")
        );
    }
}
