package me.nightwarrior.epayconsumes.repository;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Merchant;
import me.nightwarrior.epayconsumes.model.MerchantCategory;
import me.nightwarrior.epayconsumes.model.MerchantList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class JdbcMerchantRepository implements MerchantRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM merchants", Integer.class);
    }

    @Override
    public int save(Merchant merchant) {
        log.info("Saving " + merchant.toString() + " into the database.");

        return jdbcTemplate.update(
                "INSERT INTO merchants (merchant_id, category_id, merchant_name, merchant_description, list_order) VALUES(?,?,?,?,?)",
                merchant.getMerchantId(), merchant.getCategoryId(), merchant.getMerchantName(), merchant.getMerchantDescription(), merchant.getListOrder()
        );
    }

    @Override
    public int update(Merchant merchant) {
        log.info("Updating " + merchant.toString() + " in the database.");

        return jdbcTemplate.update(
                "UPDATE merchants SET category_id = ?, merchant_name = ?, merchant_description = ?, list_order = ? WHERE merchant_id = ?",
                merchant.getCategoryId(), merchant.getMerchantName(), merchant.getMerchantDescription(), merchant.getListOrder(), merchant.getMerchantId()
        );
    }

    @Override
    public int deleteById(Long id) {
        log.info("Deleting a merchant witn id " + id + " from the database.");


        return jdbcTemplate.update(
                "DELETE merchants WHERE merchant_id = ?",
                id);
    }

    @Override
    public void empty() {
        log.info("Deleting all records from the merchants table.");

        jdbcTemplate.update("DELETE FROM merchants");
    }

    @Override
    public List<Merchant> findAll() {
        log.info("Getting all records from the merchants table.");


        return jdbcTemplate.query(
                "SELECT * FROM merchants",this::mapRowToMerchant);
    }

    @Override
    public List<Merchant> findById(Long id) {
        log.info("Getting the merchant with id" + id +  " from the database.");


        return jdbcTemplate.query(
                "SELECT * FROM merchants WHERE merchant_id = ?", this::mapRowToMerchant, id);
    }

    @Override
    public List<MerchantList> findAllWithCategory() {
        return jdbcTemplate.query("SELECT * FROM merchants FULL OUTER JOIN merchant_categories " +
                "ON merchants.category_id = merchant_categories.category_id ORDER BY merchant_categories.category_id", this::mapRowToMerchantList);
    }

    @Override
    public MerchantList mapRowToMerchantList(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }

    @Override
    public Merchant mapRowToMerchant(ResultSet rs, int rowNum) throws SQLException {
        return new Merchant(
                rs.getInt("merchant_id"),
                rs.getInt("category_id"),
                rs.getString("merchant_name"),
                rs.getString("merchant_description"),
                rs.getInt("list_order")
        );
    }
}
