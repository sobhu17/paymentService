package payment.service.paymentservice.services.paymentGateway;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentGatewaySelectionStrategy {

    private RazorpayPaymentGateway razorpayPaymentGateway;
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGatewaySelectionStrategy(RazorpayPaymentGateway razorpayPaymentGateway , StripePaymentGateway stripePaymentGateway){
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway(){
//        int randomInt = new Random().nextInt();
//
//        if(randomInt % 2 == 0){
//            return razorpayPaymentGateway;
//        }
//        else{
//            return stripePaymentGateway;
//        }

        return razorpayPaymentGateway;
    }

}
