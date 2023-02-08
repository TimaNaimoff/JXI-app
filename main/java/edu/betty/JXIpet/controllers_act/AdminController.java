package edu.betty.JXIpet.controllers_act;

import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private PeopleService peopleService;
    @Autowired
    public AdminController(PeopleService peopleService){
        this.peopleService=peopleService;
    }
    @GetMapping()
    public String getAdmin(Model model, @ModelAttribute("person") Person person) throws SQLException {
        model.addAttribute("people",peopleService.findAll());
        return "admin/adminus";
    }
    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person")Person person){
        System.out.println(person.getId());
        return "redirect:/people";
    }
}
