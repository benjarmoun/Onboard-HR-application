package com.example.onboard.services;


import com.example.onboard.entities.Employee;
import com.example.onboard.helpers.Enum;
import com.example.onboard.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository customerRepository;

    @Autowired
    public EmployeeService(EmployeeRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Employee save(Employee customer){
        return customerRepository.save(customer);
    }

    public List<Employee> getAllCustomers(){
        return customerRepository.findAll();
    }


    public Employee getCustomerById(Long id){
        Optional customer = customerRepository.findById(id);
        return customer.isPresent()? (Employee) customer.get() : null;
    }

    public Employee getCustomerByEmail(String email){
        System.out.println("wa7ed");
        Optional customer = customerRepository.findByEmail(email);
        System.out.println("jouj "+customer);
        return customer.isPresent() ? (Employee) customer.get() : null;
    }

    public void deleteCustomerById(Long id){
        if(getCustomerById(id) != null)
            customerRepository.deleteById(id);
    }


    public UserDetails findByEmail(String email) {
        Employee user = customerRepository.findAll()
                .stream()
                .filter(u -> (u.getEmail()).equals(email))
                .findFirst()
                .orElse(null);
        return user != null ? new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(Enum.role.Employee.toString()))) : null ;
    }
}
