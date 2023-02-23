package com.example.onboard.helpers;

import com.example.onboard.entities.Employee;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class EmployeeLoggedIn {
    private Employee epmloyee;
    private String Token;
}
