package edu.betty.JXIpet.controllers_act;


import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.services.PeopleService;
import edu.betty.JXIpet.services.RegistrationService;
import edu.betty.JXIpet.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    @Autowired
    public AuthController(PersonValidator personValidator,RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService=registrationService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "/auth/registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person")  Person person,
                                      BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
//        if(bindingResult.hasErrors()){
//            return "/auth/registration";
//        }
        System.out.println("Pre regging! " +person);
        registrationService.register(person);
        return "redirect:/auth/login";
    }

}
