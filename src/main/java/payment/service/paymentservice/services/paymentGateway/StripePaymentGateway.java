package payment.service.paymentservice.services.paymentGateway;


import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway{

    @Value("sk_test_51OZr4ESCWfVCCnrMZv6eB8ZmBFEUBhrZEk7Viow5sZTWs5VSfNVDKh5lTiaqCR0x1wo9SBRUDKKoY2FU3bnZdjpc0036SVnpDN")
    private String apiKey;
    @Override
    public String generatePaymentLink(String orderId , String email , String phoneNumber , Long amount) {
        Stripe.apiKey = apiKey;


        PriceCreateParams params1 =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Burnol").build()
                        )
                        .build();
        Price price = null;
        try {
            price = Price.create(params1);
        }
        catch (Exception e){
            System.out.println(e);
        }

        PaymentLinkCreateParams params2 =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = null;
        try {
            paymentLink = PaymentLink.create(params2);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return paymentLink.toString();
    }
}
