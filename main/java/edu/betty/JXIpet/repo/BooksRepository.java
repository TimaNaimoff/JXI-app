package edu.betty.JXIpet.repo;


import edu.betty.JXIpet.business.Book;
import edu.betty.JXIpet.business.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
    List<Book> findByAuthorName(String name);

    List<Book> findByPerson(Person person);

    List<Book> findByTitle(String title);
}
