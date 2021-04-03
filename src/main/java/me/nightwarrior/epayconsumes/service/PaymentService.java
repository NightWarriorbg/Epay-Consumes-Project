package me.nightwarrior.epayconsumes.service;

import me.nightwarrior.epayconsumes.model.MerchantListMockup;
import me.nightwarrior.epayconsumes.model.Request;
import me.nightwarrior.epayconsumes.model.Response;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    public MerchantListMockup getMerchantsData();

    public Response checkBill(Request request);

    public Response checkBillBlocked(Request request);

    public Response payBill(Request request);

    public Response reverseBill(Request request);



}
