package com.example.onboard.controllers;

import com.example.onboard.dto.AuthenticationRequest;
import com.example.onboard.entities.Employee;
import com.example.onboard.helpers.EmployeeLoggedIn;
import com.example.onboard.helpers.Enum;
import com.example.onboard.helpers.JwtUtils;
import com.example.onboard.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
        if (setAuthenticationManager(request.getEmail(), request.getPassword())){
            if(employeeService.getEmployeeByEmail(request.getEmail()).getRole().equals(Enum.role.RH.toString())){
                final UserDetails user = employeeService.findByEmail(request.getEmail());

                if(user != null){
                    EmployeeLoggedIn loggedIn = new EmployeeLoggedIn();
                    Employee employee = employeeService.getEmployeeByEmail(user.getUsername());
                    loggedIn.setEmployee(employee);
                    loggedIn.setToken(jwtUtils.generateToken(user));
                    return ResponseEntity.ok(loggedIn);
                }
                return ResponseEntity.status(400).body("An error has occurred");
            }
            return ResponseEntity.status(400).body("Not authorized");
        }
        return ResponseEntity.status(400).body("Wrong Email or Password");
    }


    @PostMapping("employee")
    public ResponseEntity<Object> customerAuthentication(
            @RequestBody AuthenticationRequest request
    ){
        if (setAuthenticationManager(request.getEmail(), request.getPassword())) {
            if (employeeService.getEmployeeByEmail(request.getEmail()).getRole().equals(Enum.role.Employee.toString())) {
                final UserDetails userCustomer = employeeService.findByEmail(request.getEmail());
                if (userCustomer != null) {
                    EmployeeLoggedIn loggedIn = new EmployeeLoggedIn();
                    Employee employee = employeeService.getEmployeeByEmail(request.getEmail());
                    employee.setPassword(null);
                    loggedIn.setEmployee(employee);
                    loggedIn.setToken(jwtUtils.generateToken(userCustomer));
                    return ResponseEntity.ok(loggedIn);
                }
                return ResponseEntity.status(400).body("Some error has occurred");
            }
            return ResponseEntity.status(400).body("Not authorized");
        }
        return ResponseEntity.status(400).body("Wrong Email or Password");
    }

    public boolean setAuthenticationManager(String email, String password){
        UsernamePasswordAuthenticationToken tkn = new UsernamePasswordAuthenticationToken(email, password);
        try {
            if (authenticationManager.authenticate(tkn).isAuthenticated()){
                return true;
            }
        } catch (AuthenticationException e) {
            return false;
        }
        return false;
    }

}
