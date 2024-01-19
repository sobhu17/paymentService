package payment.service.paymentservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import payment.service.paymentservice.services.paymentGateway.PaymentGateway;
import payment.service.paymentservice.services.paymentGateway.PaymentGatewaySelectionStrategy;

@Service
public class PaymentService {

    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;

    public PaymentService(PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy){
        this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }

    public String initiatePayment(String orderId , String email , String phoneNumber , Long amount){

        // Order order = orderService.getOrderDetails(orderId)
        // Long amount = order.getAmount();
        // double amount = 10.10; // store number * 100
        // String orderId, String email, String phoneNumber, Long amount
        // Long amount = 1010L; // 10.00 => 1000

        PaymentGateway paymentGateway = paymentGatewaySelectionStrategy.getBestPaymentGateway();

        return paymentGateway.generatePaymentLink(orderId , email , phoneNumber , amount);
    }
}
