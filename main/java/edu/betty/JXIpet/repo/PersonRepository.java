package edu.betty.JXIpet.repo;

import edu.betty.JXIpet.business.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer>{
    @Query("SELECT p FROM Person p WHERE p.name LIKE %:name%")
    Optional<Person> findByUserName(@Param("name")String name);
}
