package edu.betty.JXIpet.services;

import edu.betty.JXIpet.business.Person;
import edu.betty.JXIpet.repo.PeopleRepository;
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
        Person optional = peopleRepository.findByName(username);
        if(optional==null)throw new UsernameNotFoundException("User not found!");
        return new PersonDetails(optional);
    }

}
