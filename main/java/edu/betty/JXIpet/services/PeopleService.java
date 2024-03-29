package edu.betty.JXIpet.services;

import edu.betty.JXIpet.business.Mood;
import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.repo.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public void test(){
        System.out.println("Testing started");
    }
    public Person findOnePerson(int id){

        Person person = peopleRepository.findById(id).orElse(null);
        assert person != null;
        if(person.getCreatedTime()==null||LocalDateTime.now().isBefore(person.getCreatedTime().plusDays(10))){
            person.setStatus(false);
        }

        person.setStatus(true);
        return person;
    }
    @Transactional
    public void save(Person person){
        person.setMood(Mood.HAPPY);
        person.setCreatedTime(LocalDateTime.now());
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id,Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
    public boolean checkByName(String name){
        return false;
    }
}
