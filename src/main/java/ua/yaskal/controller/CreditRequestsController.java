/*
package ua.yaskal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.model.service.CreditRequestService;
import ua.yaskal.model.entity.CreditRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping(value = "/admin/credit_requests")
public class CreditRequestsController {
    private CreditRequestService creditRequestService;

    public CreditRequestsController(CreditRequestService creditRequestService) {
        this.creditRequestService = creditRequestService;
    }

    @GetMapping("/all")
    public String allCreditRequests(Map<String, Object> model){
        model.put("creditRequests", creditRequestService.getAllCreditRequests());
        return "admin/all_credit_requests";
    }

    @GetMapping("/pending")
    public String pendingCreditRequests(Map<String, Object> model){
        model.put("creditRequests", selectCreditRequests(CreditRequest.CreditRequestStatus.PENDING));
        return "admin/all_credit_requests";
    }

    @GetMapping("/rejected")
    public String rejectedCreditRequests(Map<String, Object> model){
        model.put("creditRequests", selectCreditRequests(CreditRequest.CreditRequestStatus.REJECTED));
        return "admin/all_credit_requests";
    }

    @GetMapping("/approved")
    public String approvedCreditRequests(Map<String, Object> model){
        model.put("creditRequests", selectCreditRequests(CreditRequest.CreditRequestStatus.APPROVED));
        return "admin/all_credit_requests";
    }

    private Iterable<CreditRequest> selectCreditRequests(CreditRequest.CreditRequestStatus status){
        Iterable<CreditRequest> creditRequests = creditRequestService.getAllCreditRequests();
        List<CreditRequest> result = new ArrayList<>();
        for (CreditRequest i: creditRequests){
            if(i.getCreditRequestStatus().equals(status)){
                result.add(i);
            }

        }
        return result;
    }
}
*/
