package edu.betty.JXIpet.utils;


import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.services.PeopleDetailService;
import edu.betty.JXIpet.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.naming.NameNotFoundException;

@Component
public class PersonValidator implements Validator {
    @Autowired
    private final PeopleDetailService peopleDetailService;
    @Autowired
    public PersonValidator(PeopleDetailService peopleDetailService){
        this.peopleDetailService=peopleDetailService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person=(Person)o;

            try {
//                throw new NameNotFoundException();
                peopleDetailService.loadUserByUsername(person.getName());
            } catch (UsernameNotFoundException e) {
                return;
            }
            errors.rejectValue("name","We have person with this name!");
        }



}
