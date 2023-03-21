package galewarning.rewardsrestful.controller;

import galewarning.rewardsrestful.model.Rewards;
import galewarning.rewardsrestful.model.Transaction;
import galewarning.rewardsrestful.service.RewardsService;
import galewarning.rewardsrestful.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api")
public class RewardsController {
    private RewardsService rewardsService;

    @Autowired
    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<?> getRewardsByCustomerId(@PathVariable("customerId") long customerId) {
        Rewards rewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.valueOf(200));
    }

    @GetMapping("/transactions/firstmonth/{customerId}")
    public ResponseEntity<?> getFirstMonthTransactionsByCustomerId(@PathVariable("customerId") long customerId) {
        List<Transaction> transactions = rewardsService.firstMonthTransactions(customerId);
        return new ResponseEntity<>(transactions, HttpStatus.valueOf(200));
    }

    @GetMapping("/transactions/secondmonth/{customerId}")
    public ResponseEntity<?> getSecondMonthTransactionsByCustomerId(@PathVariable("customerId") long customerId) {
        List<Transaction> transactions = rewardsService.secondMonthTransactions(customerId);
        return new ResponseEntity<>(transactions, HttpStatus.valueOf(200));
    }

    @GetMapping("/transactions/thirdmonth/{customerId}")
    public ResponseEntity<?> getThirdMonthTransactionsByCustomerId(@PathVariable("customerId") long customerId) {
        List<Transaction> transactions = rewardsService.thirdMonthTransactions(customerId);
        return new ResponseEntity<>(transactions, HttpStatus.valueOf(200));
    }

    @GetMapping("/rewards/firstmonth/{customerId}")
    public ResponseEntity<?> getFirstMonthRewardsByCustomerId(@PathVariable("customerId") long customerId) {
        Long rewards = rewardsService.firstMonthRewards(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.valueOf(200));
    }

    @GetMapping("/rewards/secondmonth/{customerId}")
    public ResponseEntity<?> getSecondMonthRewardsByCustomerId(@PathVariable("customerId") long customerId) {
        Long rewards = rewardsService.secondMonthRewards(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.valueOf(200));
    }

    @GetMapping("/rewards/thirdmonth/{customerId}")
    public ResponseEntity<?> getThirdMonthRewardsByCustomerId(@PathVariable("customerId") long customerId) {
        Long rewards = rewardsService.thirdMonthRewards(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.valueOf(200));
    }
}
