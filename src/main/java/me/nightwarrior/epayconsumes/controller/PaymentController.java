package me.nightwarrior.epayconsumes.controller;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Due;
import me.nightwarrior.epayconsumes.model.Merchant;
import me.nightwarrior.epayconsumes.model.Registration;
import me.nightwarrior.epayconsumes.model.Response;
import me.nightwarrior.epayconsumes.service.BillService;
import me.nightwarrior.epayconsumes.service.DueService;
import me.nightwarrior.epayconsumes.service.MerchantService;
import me.nightwarrior.epayconsumes.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    MerchantService merchantService;

    @Autowired
    BillService billService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    DueService dueService;

    @GetMapping(value = "/getMerchants", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Merchant> getMerchants() {
        return merchantService.getAllMerchants();
    }

    @GetMapping(value = "/checkBill", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Response checkBill(@RequestParam Integer MerchantId, @RequestParam Integer SubscrNumber) {
        return billService.checkBill();
    }

    @PostMapping(value = "/saveRegistration", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Registration saveRegistration(@RequestBody Registration request) {
        return registrationService.saveRegistration(request);
    }

    @RequestMapping(value = "/editRegistration", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Registration editRegistration(@RequestBody Registration request) {
        return registrationService.editRegistration(request, SubscriberId);
    }

    @DeleteMapping(value = "/deleteRegistration", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Registration deleteRegistration(@RequestBody Registration request, @RequestParam Long SubscriberId) {
        return registrationService.deleteRegistration(request, SubscriberId);
    }

    @GetMapping(value = "/getPendingDues", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Due> getPendingDues() {
        return dueService.getPendingDues();
    }
}
