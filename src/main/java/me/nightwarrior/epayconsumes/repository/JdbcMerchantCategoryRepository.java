package me.nightwarrior.epayconsumes.repository;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.MerchantCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class JdbcMerchantCategoryRepository implements MerchantCategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM merchant_categories", Integer.class);
    }

    @Override
    public int save(MerchantCategory merchantCategory) {
        log.info("Saving " + merchantCategory.toString() + " into the database.");

        return jdbcTemplate.update(
                "INSERT INTO merchant_categories (category_name, category_type, category_description, list_order) VALUES(?,?,?,?)",
                merchantCategory.getCategoryName(), merchantCategory.getCategoryType(), merchantCategory.getCategoryDescription(), merchantCategory.getListOrder()
        );
    }

    @Override
    public int update(MerchantCategory merchantCategory) {
        log.info("Updating " + merchantCategory.toString() + " in the database.");


        return jdbcTemplate.update(
                "UPDATE merchants SET category_name = ?, category_type = ?, category_description = ?, list_order = ? WHERE category_id = ?",
                merchantCategory.getCategoryName(), merchantCategory.getCategoryType(), merchantCategory.getCategoryDescription(), merchantCategory.getListOrder(),
                merchantCategory.getCategoryId()
        );
    }

    @Override
    public int deleteById(Long id) {
        log.info("Deleting a merchant category with id " + id + " from the database.");


        return jdbcTemplate.update(
                "DELETE merchant_categories WHERE category_id = ?",
                id);
    }

    @Override
    public void empty() {
        log.info("Deleting all records from the merchant categories table.");

        jdbcTemplate.update("DELETE FROM merchant_categories");
    }

    @Override
    public List<MerchantCategory> findAll() {
        log.info("Getting all records from the merchant categories table.");


        return jdbcTemplate.query(
                "SELECT * FROM merchant_categories",this::mapRowToMerchantCategory);
    }

    @Override
    public List<MerchantCategory> findById(Long id) {
        log.info("Getting the merchant_category with id" + id +  " from the database.");


        return jdbcTemplate.query(
                "SELECT * FROM merchant_categories WHERE category_id = ?", this::mapRowToMerchantCategory, id);
    }

    @Override
    public MerchantCategory mapRowToMerchantCategory(ResultSet rs, int rowNum) throws SQLException {
        return new MerchantCategory(
                rs.getString("category_name"),
                rs.getString("category_type"),
                rs.getString("category_description"),
                rs.getInt("list_order")
        );
    }

}
