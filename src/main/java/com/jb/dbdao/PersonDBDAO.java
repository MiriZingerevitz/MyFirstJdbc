package com.jb.dbdao;

import com.jb.beans.Hobby;
import com.jb.beans.Person;
import com.jb.dao.PersonDAO;
import com.jb.utils.DBUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDBDAO implements PersonDAO {
    private static final String QUERY_INSERT = "INSERT INTO `java129`.`person`\n" +
            "(`id`,\n" +
            "`name`,\n" +
            "`city`,\n" +
            "`birthday`,\n" +
            "`hobby`)\n" +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String QUERY_UPDATE = "UPDATE `java129`.`person`\n" +
            "SET\n" +
            "`name` = ?,\n" +
            "`city` = ?,\n" +
            "`birthday` = ?,\n" +
            "`hobby` = ?\n" +
            "WHERE `id` = ?;";
    private static final String QUERY_DELETE = "DELETE FROM `java129`.`person`\n" +
            "WHERE  (`id` = ?);";
    private static final String QUERY_GET_ONE = "SELECT * FROM `java129`.`person` WHERE (`id` = ?);";
    private static final String QUERY_GET_ALL = "SELECT * FROM `java129`.`person`";


    @Override
    public void addPerson(Person person) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, person.getId());
        map.put(2, person.getName());
        map.put(3, person.getCity());
        map.put(4, person.getBirthday());
        map.put(5, person.getHobby());
        DBUtils.runQuery(QUERY_INSERT,map);
    }

    @Override
    public void updatePerson(Person person) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, person.getName());
        map.put(2, person.getCity());
        map.put(3, person.getBirthday());
        map.put(4, person.getHobby());
        map.put(5, person.getId());
      System.out.println(map);
        DBUtils.runQuery(QUERY_UPDATE, map);
    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, person.getId());
        DBUtils.runQuery(QUERY_DELETE, map);
    }

    @Override
    public Person getSinglePerson(int id) throws SQLException {
        Person person = null;
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, id);

        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_GET_ONE, map);
        resultSet.next();

        //int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String city = resultSet.getString(3);
        //LocalDate birthday = resultSet.getDate(4);
        LocalDate birthday = resultSet.getDate(4).toLocalDate();
        Hobby hobby= Hobby.valueOf(resultSet.getString(5));
         person = new Person(id, name,city,birthday,hobby);



        return person;
    }

    @Override
    public List<Person> getAllPersons() throws SQLException {
        List<Person> persons = new ArrayList<>();

        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_GET_ALL);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String city = resultSet.getString(3);
            LocalDate birthday = resultSet.getDate(4).toLocalDate();
            Hobby hobby= Hobby.valueOf(resultSet.getString(5));
            Person person = new Person(id, name,city,birthday,hobby);
            persons.add(person);
        }

        return persons;
    }
}
