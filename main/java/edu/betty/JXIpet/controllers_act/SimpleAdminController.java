package edu.betty.JXIpet.controllers_act;

import edu.betty.JXIpet.security.PersonDetails;
import edu.betty.JXIpet.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleAdminController {
    @Autowired
    private final AdminService adminService;
    public SimpleAdminController(AdminService adminService){
        this.adminService=adminService;
    }
    @GetMapping("/hello")
    public String sayHello(){
        return "/people/hello";
    }
    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails=(PersonDetails)authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "/people/hello";
    }
    @GetMapping("/adminer")
    public String toAdmin(){
        adminService.doAdminStuff();
        return "/admin/admin";
    }
}
