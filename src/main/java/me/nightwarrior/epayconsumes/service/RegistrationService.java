package me.nightwarrior.epayconsumes.service;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Registration;
import me.nightwarrior.epayconsumes.model.Subscription;
import me.nightwarrior.epayconsumes.repository.JdbcSubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistrationService {

    @Autowired
    JdbcSubscriberRepository jdbcSubscriberRepository;

    public Registration saveRegistration(Registration request) {

        Subscription subscription = new Subscription(
                1, request.getSubscriptionAlias(), request.getMerchantRefNo(), request.getMerchantId(), request.getCustomerName(),
                request.getCustomerAddress(), request.getShortDesc(), request.getPayFrom(), request.getPayAccount(), request.getAutomaticPayment(),
                null,  request.getRetryDays(), null, null, null, null,
                null, null, null, null);

        jdbcSubscriberRepository.save(subscription);
        return request;
    }

    public Registration editRegistration(Registration request, Long id) {

        Subscription subscription = new Subscription(
                1, request.getSubscriptionAlias(), request.getMerchantRefNo(), request.getMerchantId(), request.getCustomerName(),
                request.getCustomerAddress(), request.getShortDesc(), request.getPayFrom(), request.getPayAccount(), request.getAutomaticPayment(),
                null,  request.getRetryDays(), null, null, null, null,
                null, null, null, null);

        jdbcSubscriberRepository.update(subscription, id);
        return request;
    }

    public Registration deleteRegistration(Registration request, Long id) {
        jdbcSubscriberRepository.deleteById(id);
        return request;
    }
}
