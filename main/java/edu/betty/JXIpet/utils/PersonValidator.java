package edu.betty.JXIpet.utils;


import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.services.PeopleDetailService;
import edu.betty.JXIpet.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(peopleDetailService.loadUserByUsername(person.getName())==null){
            errors.rejectValue("email","","This is not correct!");
            try {
                throw new NameNotFoundException();
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}
