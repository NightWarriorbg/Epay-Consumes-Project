package me.nightwarrior.epayconsumes.service;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Merchant;
import me.nightwarrior.epayconsumes.model.MerchantCategory;
import me.nightwarrior.epayconsumes.model.MerchantMockup;
import me.nightwarrior.epayconsumes.repository.JdbcMerchantCategoryRepository;
import me.nightwarrior.epayconsumes.repository.JdbcMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class MerchantService {

    @Autowired
    PaymentService paymentService;

    @Autowired
    JdbcMerchantRepository jdbcMerchantRepository;

    @Autowired
    JdbcMerchantCategoryRepository jdbcMerchantCategoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SQLException.class)
    public void populateMerchants() {
        List<MerchantMockup> merchants = paymentService.getMerchantsData().getMerchantList();
        for (MerchantMockup merchantMockup : merchants) {

            Integer merchantId = merchantMockup.getMerchantId();
            String merchantName = merchantMockup.getFullName();
            String merchantDescription = merchantMockup.getMerchantShortDesc();
            String categoryName = merchantMockup.getCategoryName();
            String categoryDescription = merchantMockup.getMerchantShortDesc();
            Integer categoryId = merchantMockup.getCategory();


            Merchant merchant = new Merchant(merchantId, categoryId, merchantName, merchantDescription, 1);
            MerchantCategory merchantCategory = new MerchantCategory(categoryName, "1", categoryDescription, 1);

            log.info("Inserting merchants and merchant categories into the database.");

            jdbcMerchantRepository.empty();
            jdbcMerchantCategoryRepository.empty();
            jdbcMerchantRepository.save(merchant);
            jdbcMerchantCategoryRepository.save(merchantCategory);
        }
    }

    public List<Merchant> getAllMerchants() {
        return jdbcMerchantRepository.findAll();
    }
}
