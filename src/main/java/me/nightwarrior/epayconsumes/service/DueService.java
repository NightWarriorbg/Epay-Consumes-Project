package me.nightwarrior.epayconsumes.service;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Due;
import me.nightwarrior.epayconsumes.repository.JdbcDueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class DueService {

    @Autowired
    JdbcDueRepository dueRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SQLException.class)
    public void populateDues() {

        Due due1 = new Due(
                1, 0000030, "450746", 6180030, null,
                23592, null, null, "ПЕТЪР ДИМИТРОВ ВЕЛИЧКОВ", "Long Desc", null, "00"
        );

        Due due2 = new Due(
                1, 0000040, "4503746", 6180030, null,
                23592, null, null, "ПЕТЪР ДИМИТРОВ ВЕЛИЧКОВ", "Long Desc", null, "00"
        );

        Due due3 = new Due(
                1, 0000050, "4503746", 6180030, null,
                23592, null, null, "ПЕТЪР ДИМИТРОВ ВЕЛИЧКОВ", "Long Desc", null, "00"
        );

        log.info("Inserting dues into the database.");

        if (dueRepository.isEmpty()) {
            dueRepository.save(due1);
            dueRepository.save(due2);
            dueRepository.save(due3);
        }
    }

    public List<Due> getPendingDues() {
        populateDues();
        return dueRepository.findAll();
    }
}
