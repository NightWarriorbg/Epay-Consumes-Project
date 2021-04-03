package me.nightwarrior.epayconsumes;

import me.nightwarrior.epayconsumes.model.Merchant;
import me.nightwarrior.epayconsumes.repository.JdbcMerchantRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MerchantRepositoryTests {

    @Autowired
    JdbcMerchantRepository jdbcMerchantRepository;


    public void saveMerchantTest() {
        //jdbcMerchantRepository.save(new Merchant(1, "Test", "Desc", 3));
        //Assert.assertNotNull(0);
    }
}
