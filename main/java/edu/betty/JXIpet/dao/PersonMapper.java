package edu.betty.JXIpet.dao;

import edu.betty.JXIpet.business.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person=new Person();
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setId(resultSet.getInt("person_id"));
            person.setEmail(resultSet.getString("email"));


        return person;
    }
}
