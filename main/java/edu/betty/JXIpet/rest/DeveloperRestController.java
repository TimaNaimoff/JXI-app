package edu.betty.JXIpet.rest;

import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.repo.PersonRepository;
import edu.betty.JXIpet.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestController {
    @Autowired
    private PeopleService peopleService;
    @GetMapping
    public List<Person>getAll(){
        return peopleService.findAll();
    }
    @GetMapping("/{id}")
    public Person getById(@PathVariable("id") Integer id){
        return peopleService.findOnePerson(id);
    }

}
