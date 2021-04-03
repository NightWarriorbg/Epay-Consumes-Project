package me.nightwarrior.epayconsumes.repository;

import me.nightwarrior.epayconsumes.model.MerchantCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MerchantCategoryRepository {

    int count();

    int save(MerchantCategory merchantCategory);

    int update(MerchantCategory merchantCategory);

    int deleteById(Long id);

    void empty();

    List<MerchantCategory> findAll();

    List<MerchantCategory> findById(Long id);

    MerchantCategory mapRowToMerchantCategory(ResultSet resultSet, int rowNumn) throws SQLException;

}
