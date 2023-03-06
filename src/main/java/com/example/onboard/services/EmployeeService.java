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
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllemployees(){
        return employeeRepository.findAll();
    }


    public Employee getEmployeeById(Long id){
        Optional employee = employeeRepository.findById(id);
        return employee.isPresent()? (Employee) employee.get() : null;
    }

    public Employee getEmployeeByEmail(String email){
        Optional employee = employeeRepository.findByEmail(email);
        return employee.isPresent() ? (Employee) employee.get() : null;
    }

    public void deleteEmployeeById(Long id){
        if(getEmployeeById(id) != null)
            employeeRepository.deleteById(id);
    }


    public UserDetails findByEmail(String email) {
        Employee user = employeeRepository.findAll()
                .stream()
                .filter(u -> (u.getEmail()).equals(email))
                .findFirst()
                .orElse(null);

        System.out.println(user);
        if(user != null && user.getRole().equals(Enum.role.Employee.toString())){
            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(Enum.role.Employee.toString())));
        } else if (user.getRole().equals(Enum.role.RH.toString())) {
            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(Enum.role.RH.toString())));
        }
    return null;
    }
}
