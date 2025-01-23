package com.sali.EmployeeManagement.controller;

import com.sali.EmployeeManagement.entity.Employee;
import com.sali.EmployeeManagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final EmployeeService employeeService;

    public UserController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/profile")
    public String viewProfile(Model model, Principal principal) {
        String email = principal.getName();
        Employee employee = employeeService.getEmployeeByEmail(email);
        model.addAttribute("employee", employee);
        return "user/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfileForm(Model model, Principal principal) {
        String email = principal.getName();
        Employee employee = employeeService.getEmployeeByEmail(email);
        model.addAttribute("employee", employee);
        return "user/edit_profile";
    }

    @PostMapping("/profile/{id}")
    public String updateProfile(@PathVariable Long id,
                                @ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(id, employee);
        return "redirect:/user/profile";
    }
}
