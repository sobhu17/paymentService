package payment.service.paymentservice.services.paymentGateway;

import com.razorpay.RazorpayException;

public interface PaymentGateway {

    String generatePaymentLink(String orderId , String email , String phoneNumber , Long amount);
}
