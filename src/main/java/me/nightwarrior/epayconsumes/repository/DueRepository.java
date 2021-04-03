package me.nightwarrior.epayconsumes.repository;

import me.nightwarrior.epayconsumes.model.Due;
import me.nightwarrior.epayconsumes.model.Merchant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DueRepository {

    int count();

    int save(Due due);

    int update(Due due);

    int deleteById(Long id);

    void empty();

    boolean isEmpty();

    List<Due> findAll();

    List<Due> findById(Long id);

    Due mapRowToDue(ResultSet resultSet, int rowNumn) throws SQLException;

}
