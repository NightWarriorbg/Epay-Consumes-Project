package me.nightwarrior.epayconsumes.service;

import lombok.extern.slf4j.Slf4j;
import me.nightwarrior.epayconsumes.model.MerchantListMockup;
import me.nightwarrior.epayconsumes.model.Request;
import me.nightwarrior.epayconsumes.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mockup.server.uri}")
    private String BASE_URL;

    @Override
    public MerchantListMockup getMerchantsData() {
        MerchantListMockup merchantListMockup = restTemplate.getForObject(BASE_URL + "getMerchantsData", MerchantListMockup.class);
        log.info(merchantListMockup.toString());
        return merchantListMockup;
    }

    @Override
    public Response checkBill(Request request) {
        Response response = restTemplate.postForObject(BASE_URL + "checkBill", request, Response.class);
        log.info(response.toString());
        return response;
    }

    @Override
    public Response checkBillBlocked(Request request) {
        Response response = restTemplate.postForObject(BASE_URL + "checkBillBlocked", request, Response.class);
        log.info(response.toString());
        return response;
    }

    @Override
    public Response payBill(Request request) {
        Response response = restTemplate.postForObject(BASE_URL + "payBill", request, Response.class);
        log.info(response.toString());
        return response;
    }

    @Override
    public Response reverseBill(Request request) {
        Response response = restTemplate.postForObject(BASE_URL + "reverseBill", request, Response.class);
        log.info(response.toString());
        return response;
    }

}
