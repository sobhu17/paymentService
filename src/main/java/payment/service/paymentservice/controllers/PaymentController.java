package payment.service.paymentservice.controllers;


import org.springframework.web.bind.annotation.*;
import payment.service.paymentservice.DTOs.InitiatePaymentRequestDto;
import payment.service.paymentservice.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }


    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto request){
        return paymentService.initiatePayment(request.getOrderId() , request.getEmail() , request.getPhoneNumber() , request.getAmount());
    }
}
