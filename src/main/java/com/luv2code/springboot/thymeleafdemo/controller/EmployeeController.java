package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data


	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService){
		employeeService=theEmployeeService;
	}


//	@PostConstruct
//	private void loadData() {
//
//		// create employees
//		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
//		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
//		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");
//
//		// create the list
//		theEmployees = new ArrayList<>();
//
//		// add to the list
//		theEmployees.add(emp1);
//		theEmployees.add(emp2);
//		theEmployees.add(emp3);
//	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees= employeeService.findAllByOrderByLastNameAsc();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
@GetMapping("/form")
	public String showFormForAdd(Model theModel){

		Employee theEmployee = new Employee();

		theModel.addAttribute("employee",theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/updateForm")
	public String updateEmployee(Model theModel, @RequestParam("employeeId") int Id){
       Employee emp=employeeService.findById(Id);

	   theModel.addAttribute("employee",emp);

	   return "employees/employee-form";
	}


@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        try{
			employeeService.save(theEmployee);

		}
		catch (Error err){
			System.out.println(err);
		}
		return "redirect:/employees/list";
}

@GetMapping("delete")
	public String deleteEmp(@RequestParam("employeeId") int Id,Model theModel){

		employeeService.deleteById(Id);

		return "redirect:/employees/list";

}
}











