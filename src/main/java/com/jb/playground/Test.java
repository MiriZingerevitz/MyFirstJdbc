package com.jb.playground;

import com.jb.beans.Hobby;
import com.jb.beans.Person;
import com.jb.dao.PersonDAO;
import com.jb.db.DatabaseManager;
import com.jb.dbdao.PersonDBDAO;
import com.jb.utils.ArtUtils;
import com.jb.utils.DateUtil;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;


public class Test {
    public static void main(String[] args) throws SQLException, ParseException {
        System.out.println("START");
        //DatabaseManager.dropSchema();
        DatabaseManager.createSchema();
        DatabaseManager.createTablePerson();

        PersonDAO personDAO = new PersonDBDAO();

        Person p1 = new Person(1, "Miri", "BneiBrak", LocalDate.of(2018, Month.JANUARY, 5), Hobby.SWIM);
        Person p2 = new Person(2, "Shalom", "BneiBrak", LocalDate.of(2019, Month.APRIL, 3), Hobby.PC);
        Person p3 = new Person(3, "Tamar", "BneiBrak", LocalDate.of(2021, Month.AUGUST, 1), Hobby.RUN);

        personDAO.addPerson(p1);
        personDAO.addPerson(p2);
        personDAO.addPerson(p3);
        personDAO.getAllPersons().forEach(System.out::println);


        System.out.println(ArtUtils.UPDATE);

        Person toUpdate = personDAO.getSinglePerson(2);
        toUpdate.setCity("Ramat Gan");
        personDAO.updatePerson(toUpdate);
        personDAO.getAllPersons().forEach(System.out::println);


        System.out.println(ArtUtils.DELETE);
        Person toDelete = personDAO.getSinglePerson(3);
        personDAO.deletePerson(toDelete);
        personDAO.getAllPersons().forEach(System.out::println);

        System.out.println(ArtUtils.GET_SINGLE);
        System.out.println(personDAO.getSinglePerson(2));
        System.out.println(ArtUtils.GET_ALL);
        personDAO.getAllPersons().forEach(System.out::println);
        System.out.println("END");
    }
}
