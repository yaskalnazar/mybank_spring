package ua.yaskal.controller.user;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.controller.util.ValidationUtil;
import ua.yaskal.model.dto.DepositDTO;
import ua.yaskal.model.entity.DepositAccount;
import ua.yaskal.model.service.DepositService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * This command used for getting deposit open to USER and process answer.
 * Required params: depositAmount, depositRate, monthsAmount if deposit has been requested;
 *
 * @author Nazar Yaskal
 */
@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class DepositOpenCommand{
    private final static Logger logger = Logger.getLogger(DepositOpenCommand.class);
    private ValidationUtil validationUtil;
    private DepositService depositService;
    private UserService userService;
   // private ScheduledService scheduledService;


    public DepositOpenCommand(ValidationUtil validationUtil, DepositService depositService, UserService userService) {
        this.validationUtil = validationUtil;
        this.depositService = depositService;
        this.userService = userService;
    }

    @GetMapping(value = "/account/deposit/open")
    public String getPage(){
        return JspPath.USER_DEPOSIT_OPEN;
    }

    @PostMapping(value = "/account/deposit/open")
    public String execute(@RequestParam(value = "depositAmount") BigDecimal depositAmount,
                          @RequestParam(value = "depositRate") BigDecimal depositRate,
                          @RequestParam(value = "monthsAmount") int monthsAmount,
                          Model model,
                          HttpServletRequest request) {


        if(!validationUtil.isParamValid(depositAmount.toString(),"depositAmount")){
            logger.warn("Deposit open attempt with incorrect deposit amount"+depositAmount);
            request.setAttribute("wrongInput", "wrongDepositAmount");
            return JspPath.USER_DEPOSIT_OPEN;
        }

        if(!validationUtil.isParamValid(depositRate.toString(), "depositRate")){
            logger.warn("Deposit open attempt with incorrect deposit rate"+depositRate);
            request.setAttribute("wrongInput", "wrongDepositRate");
            return JspPath.USER_DEPOSIT_OPEN;
        }

        if(!validationUtil.isParamValid(monthsAmount +"", "monthsAmount")){
            logger.warn("Deposit open attempt with incorrect months amount"+monthsAmount);
            request.setAttribute("wrongInput", "wrongMonthsAmount");
            return JspPath.USER_DEPOSIT_OPEN;
        }

        DepositDTO depositDTO = new DepositDTO(
                depositAmount,
                depositRate,
                monthsAmount,
                userService.getCurrentUser().getId());

        DepositAccount depositAccount;
        try {
            depositAccount = depositService.openNewDeposit(depositDTO);
            //scheduledService.scheduleAccounts(Collections.singletonList(depositAccount));

        } catch (Exception e){
            logger.warn("Deposit open error"+e);
            request.setAttribute("depositError", e);
            return JspPath.USER_DEPOSIT_OPEN;
        }

        logger.debug("Deposit open successfully (id:"+depositAccount.getId()+")");
        request.setAttribute("depositSuccess", depositAccount);
        return JspPath.USER_DEPOSIT_OPEN;
    }

}
