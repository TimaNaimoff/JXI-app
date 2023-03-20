package edu.betty.JXIpet.services;

import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.repo.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationService(PeopleRepository peopleRepository,PasswordEncoder passwordEncoder){
        this.peopleRepository=peopleRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Transactional
    public void register(Person person){
//        System.out.println("FIRST PASSWORD: "+person.getPassword().replaceFirst("a","b"));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
//        person.setPassword(person.getPassword().replaceFirst("a","y"));
        System.out.println("Encoding PASSWORD: "+person.getPassword());
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
//        $10$fNL3DBCQz3yNQehlaPil1.KSc0DQCkyclA3xHPg3nO/UMg0bMRzLy
//        $2a$10$fNL3DBCQz3yNQehlaPil1.KSc0DQCkyclA3xHPg3nO/UMg0bMRzLy
    }

}
