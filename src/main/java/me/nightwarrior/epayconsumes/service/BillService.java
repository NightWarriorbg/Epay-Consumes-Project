package me.nightwarrior.epayconsumes.service;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.Request;
import me.nightwarrior.epayconsumes.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillService {

    @Autowired
    PaymentService paymentService;

    public Response checkBill() {
        Request request = new Request(null, null, null, "0000001", "1000007");
        Response response = paymentService.checkBill(request);
        return response;
    }
}
