package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Employee;
import com.cydeo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//(also include @Component)
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    //@Autowired->this annotation is used for injecting
    // employee service to employee controller
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/register")
    public String createEmployee(Model model){

        model.addAttribute("employee", new Employee());
        model.addAttribute("statesList", DataGenerator.getAllStates());
        return "employee/employee-create";
    }

    @PostMapping("/insert")
    public String insertEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("statesList", DataGenerator.getAllStates());
            return "employee/employee-create";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employee/list"; // use end point for redirect, then it returns
                                          // the whole method not only the html file.
    }

    @GetMapping("/list")
    public String getEmployeeList(Model model){
        model.addAttribute("employeeList", employeeService.readAllEmployee());
        return "employee/employee-list";
    }


}
