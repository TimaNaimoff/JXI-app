package edu.betty.JXIpet.services;

import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.repo.PersonRepository;
import edu.betty.JXIpet.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PeopleDetailService implements UserDetailsService {
    private final PersonRepository peopleRepository;

    @Autowired
    public PeopleDetailService(PersonRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("USERNAME:"+username);
        Optional<Person> optional = peopleRepository.findByUserName(username);
//        System.out.println(optional.get().get(0).getName()+" : NAME optional");
        System.out.println(optional);
        if(optional.isEmpty())throw new UsernameNotFoundException("Not found lok!");

        //        if(optional==null|| optional.isEmpty())throw new UsernameNotFoundException("User not found!");
//        System.out.println(optional.get(0)+" OPTIONAL");
//        String psw=optional.get().get(0).getPassword().replaceFirst("a","y");
//        optional.get().get(0).setPassword(optional.get().get(0).getPassword().strip());
        return new PersonDetails(optional.get());
    }

}
