package me.nightwarrior.epayconsumes.repository;

import me.nightwarrior.epayconsumes.model.Merchant;
import me.nightwarrior.epayconsumes.model.MerchantList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MerchantRepository {
    int count();

    int save(Merchant merchant);

    int update(Merchant merchant);

    int deleteById(Long id);

    void empty();

    List<Merchant> findAll();

    List<Merchant> findById(Long id);

    List<MerchantList> findAllWithCategory();

    MerchantList mapRowToMerchantList(ResultSet resultSet, int rowNumb) throws SQLException;

    Merchant mapRowToMerchant(ResultSet resultSet, int rowNum) throws SQLException;

}
