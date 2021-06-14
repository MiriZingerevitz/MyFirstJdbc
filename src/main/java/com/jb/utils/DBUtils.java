package com.jb.utils;

import com.jb.db.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;


public class DBUtils {

    public static void runQuery(String sql) throws SQLException {
        Connection connection = null;
        try{
            //STEP 2 - Open Connection to DB
            connection = ConnectionPool.getInstance().getConnection();

            //STEP 3 - Run your SQL instruction
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            //STEP 5 - Close Connection to DB
            ConnectionPool.getInstance().returnConnection(connection);
        }

    }

    public static void runQuery(String sql, Map<Integer,Object> map) throws SQLException {
        Connection connection = null;
        try{
            //STEP 2 - Open Connection to DB
            connection = ConnectionPool.getInstance().getConnection();

            //STEP 3 - Run your SQL instruction
            PreparedStatement statement = connection.prepareStatement(sql);

            for (Map.Entry<Integer,Object> entry: map.entrySet()) {
                int key = entry.getKey();
                Object value = entry.getValue();
                if(value instanceof String){
                    statement.setString(key,String.valueOf(value));
                }
                else if (value instanceof Integer){
                    statement.setInt(key,(int)value);
                }else if(value instanceof Float){
                    statement.setFloat(key,(float) value);
                }
                else if(value instanceof Date){
                    statement.setDate(key,(Date) value);
                }
                else if(value instanceof LocalDate){
                    statement.setDate(key, Date.valueOf((LocalDate) value));
                }

            }
            statement.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            //STEP 5 - Close Connection to DB
            ConnectionPool.getInstance().returnConnection(connection);
        }

    }

    public static ResultSet runQueryWithResults(String sql) throws SQLException {
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            resultSet = stm.executeQuery();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return resultSet;
    }

    public static ResultSet runQueryWithResults(String sql,Map<Integer,Object> map) throws SQLException {
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            for (Map.Entry<Integer,Object> entry: map.entrySet()) {
                int key = entry.getKey();
                Object value = entry.getValue();
                if(value instanceof String){
                    statement.setString(key,String.valueOf(value));
                }
                else if (value instanceof Integer){
                    statement.setInt(key,(int)value);
                }else if(value instanceof Float){
                    statement.setFloat(key,(float) value);
                }
            }
            resultSet = statement.executeQuery();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return resultSet;
    }
}


