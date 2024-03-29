package edu.betty.JXIpet.controllers_act;


import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.dao.PersonerDao;
import edu.betty.JXIpet.security.PersonDetails;
import edu.betty.JXIpet.services.BookService;
import edu.betty.JXIpet.services.PeopleService;
import edu.betty.JXIpet.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Objects;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BookService bookService;
    private final PersonValidator personValidator;
    private final PersonerDao personerDao;
    @Autowired
    public PeopleController(PeopleService peopleService,PersonValidator personValidator,BookService bookService,PersonerDao personerDao){
        this.peopleService=peopleService;
        this.personValidator=personValidator;
        this.bookService=bookService;
        this.personerDao=personerDao;
    }
    @GetMapping()
    public String index(Model model) throws SQLException {
//        bookService.findByAuthorName("The Endless Book");
//        bookService.findByPerson(peopleService.findAll().get(0));
//        peopleService.test();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        PersonDetails personDetails=(PersonDetails)auth.getPrincipal();
//        System.out.println(personDetails.getPerson());
//        personerDao.testNPlus1();
        model.addAttribute("listOfPeoples",peopleService.findAll());
        return "/people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id,Model model){
        Person person=peopleService.findOnePerson(id);
        model.addAttribute("person",person);
        System.out.println(bookService.findByPerson(person));
        model.addAttribute("books",bookService.findByPerson(person));
        return "/people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());
        return "/people/new";
    }
    @GetMapping("/test")
    public String testApp(Model model){
        return "/people/test";
    }
    @PostMapping
    public String create(@ModelAttribute("person")@Valid Person person, BindingResult resulter){
        personValidator.validate(person,resulter);
        if(resulter.hasErrors()) return "/people/new";
        peopleService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id, Model model){
          model.addAttribute("person",peopleService.findOnePerson(id));
          return "/people/edit";
    }
      @PatchMapping("/{id}")
      public String update(@ModelAttribute("person")@Valid Person person,BindingResult result,@PathVariable
              ("id")int id){
          personValidator.validate(person,result);

          if(result.hasErrors())return "/people/edit";
               peopleService.update(id,person);
               return "redirect:/people";
      }
      @DeleteMapping("/{id}")
      public String delete(@PathVariable("id")int id){
        peopleService.delete(id);
        return "redirect:/people";
      }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleController that = (PeopleController) o;
        return Objects.equals(peopleService, that.peopleService) && Objects.equals(bookService, that.bookService) && Objects.equals(personValidator, that.personValidator) && Objects.equals(personerDao, that.personerDao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peopleService, bookService, personValidator, personerDao);
    }
}
