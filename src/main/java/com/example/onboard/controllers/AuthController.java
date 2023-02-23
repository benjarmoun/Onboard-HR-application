package com.example.onboard.controllers;

import com.example.onboard.dto.AuthenticationRequest;
import com.example.onboard.entities.Employee;
import com.example.onboard.helpers.EmployeeLoggedIn;
import com.example.onboard.helpers.JwtUtils;
import com.example.onboard.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final EmployeeService employeeService;
    private final JwtUtils jwtUtils;

    @PostMapping("rh")
    public ResponseEntity<Object> agentAuthentication(
            @RequestBody AuthenticationRequest request
    ){
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        setAuthenticationManager(request.getEmail(), request.getPassword());
        System.out.println("teeeeest");
        final UserDetails user = employeeService.findByEmail(request.getEmail());
        System.out.println("uuser123"+ user);
        if(user != null){
            EmployeeLoggedIn loggedIn = new EmployeeLoggedIn();
            Employee employee = employeeService.getCustomerByEmail(user.getUsername());
            loggedIn.setEpmloyee(employee);
            System.out.println("123456789");
            loggedIn.setToken(jwtUtils.generateToken(user));
            return ResponseEntity.ok(loggedIn);
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

//    @PostMapping("customer")
//    public ResponseEntity<Object> customerAuthentication(
//            @RequestBody AuthenticationRequest request
//    ){
//        setAuthenticationManager(request.getEmail(), request.getPassword());
//        final UserDetails userCustomer = employeeService.findByEmail(request.getEmail());
//        if(userCustomer != null){
//            LoggedIn loggedIn = new LoggedIn();
//            Customer customer = employeeService.getCustomerByEmail(request.getEmail());
//            customer.setPassword(null);
//            loggedIn.setCustomer(customer);
//            loggedIn.setToken(jwtUtils.generateToken(userCustomer));
//            return ResponseEntity.ok(loggedIn);
//        }
//        return ResponseEntity.status(400).body("Some error has occurred");
//    }

    public void setAuthenticationManager(String email, String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }

}
