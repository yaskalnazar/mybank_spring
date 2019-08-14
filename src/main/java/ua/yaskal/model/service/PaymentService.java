package ua.yaskal.model.service;

import org.springframework.stereotype.Service;
import ua.yaskal.controller.user.AllUsersPaymentController;
import ua.yaskal.model.entity.Payment;
import ua.yaskal.model.exeptions.key.no.such.NoSuchPaymentException;
import ua.yaskal.model.repository.PaymentRepository;

import java.util.List;


/**
 * This service used for working with payments instance.
 *
 * @author Nazar Yaskal
 */
@Service
public class PaymentService {
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AllUsersPaymentController.class);

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    //TODO check active account by modification of entity
    public Payment addNew(Payment item){
        return paymentRepository.save(item);
    }

    public Payment getById(long id){
        return paymentRepository.findById(id).orElseThrow(NoSuchPaymentException::new);
    }

   /* public boolean updateStatusById(Payment.PaymentStatus status, long id){
        return daoFactory.createPaymentDAO().updateStatusById(status,id);
    }*/

    public List<Payment> getAllByPayerId(long payerId){
        return paymentRepository.getAllByPayerId(payerId);
    }

    public List<Payment> getAllByRequesterId(long requesterId){
        return paymentRepository.getAllByRequesterId(requesterId);
    }

    //TODO replace with own request
    public void updateStatusById(Payment.PaymentStatus status, long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(NoSuchPaymentException::new);
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
