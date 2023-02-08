package edu.betty.JXIpet.repo;

import edu.betty.JXIpet.business.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameOrderByAge(String name);

    List<Person> findByEmail(String name);

    List<Person> findByNameStartingWith(String startingWith);

    List<Person> findByNameOrEmail(String name,String email);


    @Query("FROM Person")
    List<Person> getPersons();
    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.list")
    List<Person>getPersonsJoin();

}
