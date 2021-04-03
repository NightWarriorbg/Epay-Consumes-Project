package me.nightwarrior.epayconsumes.repository;

import me.nightwarrior.epayconsumes.model.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionRepository {

    int count();

    int save(Subscription subscription);

    int update(Subscription subscription, Long id);

    int deleteById(Long id);

    void empty();

    List<Subscription> findAll();

    Subscription findById(Long id);

    Subscription mapRowToSubscriber(ResultSet resultSet, int rowNumn) throws SQLException;

}
