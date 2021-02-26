package me.nightwarrior.epayconsumes;

import me.nightwarrior.epayconsumes.model.MerchantList;
import me.nightwarrior.epayconsumes.model.Request;
import me.nightwarrior.epayconsumes.model.Response;
import me.nightwarrior.epayconsumes.service.PaymentServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;


@SpringBootTest
class PaymentServiceImplTests {

    @Autowired
    PaymentServiceImpl paymentService;

    @Test
    public void getMerchantsData() throws Exception {
        MerchantList merchantList = paymentService.getMerchantsData();
        Assert.assertEquals((long) merchantList.getMerchants(), getRandomInt(0, 3));
    }

    @Test
    public void  checkBillTest() throws Exception {

        Request request = new Request(null, null, null, "0000001", "1000007");
        Assert.assertNotNull(request.getMerchantId());
        Assert.assertNotNull(request.getSubscrNumber());

        Response response = paymentService.checkBill(request);
        Assert.assertNotNull(response.getStatus());
        Assert.assertNotNull(response.getValidto());
        Assert.assertNotNull(response.getAmount());

        failTestAtRandom();

    }

    @Test
    public void  checkBillBlockedTest() throws Exception {

        Request request = new Request("100000", "20101201101224", null, "0000001", "1000007");
        Assert.assertNotNull(request.getTransactionId());
        Assert.assertNotNull(request.getDt());
        Assert.assertNotNull(request.getMerchantId());
        Assert.assertNotNull(request.getSubscrNumber());

        Response response = paymentService.checkBill(request);
        Assert.assertNotNull(response.getStatus());
        Assert.assertNotNull(response.getValidto());
        Assert.assertNotNull(response.getAmount());
        //Assert.assertNotNull(response.getFee());
        //Assert.assertNotNull(response.getTotal());

        failTestAtRandom();
    }

    @Test
    public void  payBillTest() throws Exception {

        Request request = new Request("100000", "20101201101224", (double)1000, "0000001", "1000007");
        Assert.assertNotNull(request.getTransactionId());
        Assert.assertNotNull(request.getDt());
        Assert.assertNotNull(request.getAmount());
        Assert.assertNotNull(request.getMerchantId());
        Assert.assertNotNull(request.getSubscrNumber());

        Response response = paymentService.checkBill(request);
        Assert.assertNotNull(response.getStatus());

        failTestAtRandom();

    }

    @Test
    public void  reverseBillTest() throws Exception {

        Request request = new Request("100000", "20101201101224", 10.23, "0000001", "1000007");
        Assert.assertNotNull(request.getTransactionId());
        Assert.assertNotNull(request.getDt());
        Assert.assertNotNull(request.getAmount());
        Assert.assertNotNull(request.getMerchantId());
        Assert.assertNotNull(request.getSubscrNumber());

        Response response = paymentService.checkBill(request);
        Assert.assertNotNull(response.getStatus());

        failTestAtRandom();

    }

    private void failTestAtRandom() {
        if (getRandomInt(0, 4) == 2) {
            Assert.fail();
        }
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
