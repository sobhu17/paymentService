package payment.service.paymentservice.services.paymentGateway;

import com.razorpay.PaymentLink;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import java.time.Instant;

@Service
public class RazorpayPaymentGateway implements PaymentGateway {
    private RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",false);
            paymentLinkRequest.put("expire_by", 1705593044);
            paymentLinkRequest.put("reference_id",orderId);
            paymentLinkRequest.put("description","Payment for order #" + orderId);
            JSONObject customer = new JSONObject();
            customer.put("name",phoneNumber);
            customer.put("contact","Saurabh K");
            customer.put("email",email);
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",true);
            paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method","get");

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            return "something is wrong";
        }
    }
}
