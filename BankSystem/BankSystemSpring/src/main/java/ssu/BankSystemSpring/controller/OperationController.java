package ssu.BankSystemSpring.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssu.BankSystemSpring.entity.Operation;
import ssu.BankSystemSpring.service.OperationService;

import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/history/{from_account}")
    @ApiOperation("Show history by account")
    public ResponseEntity<List<Operation>> getOperationByAccount(@PathVariable("from_account") String fromAccount) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return new ResponseEntity<>(operationService.getOperationByAccount(Long.valueOf(fromAccount)), HttpStatus.OK);
    }
}
