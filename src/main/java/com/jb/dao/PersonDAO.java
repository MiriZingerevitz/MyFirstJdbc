package com.jb.dao;

import com.jb.beans.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO { //DAO - Data access Object
    void addPerson(Person person) throws SQLException;
    void updatePerson(Person person) throws SQLException;
    void deletePerson(Person person) throws SQLException;

    Person getSinglePerson(int id) throws SQLException;
    List<Person> getAllPersons() throws SQLException;
}