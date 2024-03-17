package ru.fafurin.lesson4;

import java.sql.*;

// Урок 4. PostgreSQL
public class Main {
    public static void main(String[] args) {
// 1. Что такое PostgreSQL?
        // Ответ: PostgreSQL - реляционная субд с открытым кодом

// 2. Что такое партиционирование?
        // Ответ: партиционирование - способ повышения производительности запросов за счет разбиения больших таблиц на небольшие части, называемые партициями (partitions).

// 3. Напишите запрос для вывода всех имен и Employee ID
        // Ответ:
        // Select employee_id, first_name, last_name from employees;

// 4. Напишите запрос для вывода фамилии и даты рождения
        // Ответ:
        // Select last_name, hire_date from employees;


// 5. Напишите запрос для вывода имени и фамилии и Employee ID в порядке убывания номера Employee ID
        // Ответ:
        // SELECT employee_id, first_name, last_name FROM Employees ORDER BY id DESC;








        Connect connect = new Connect();
        connect.connect();

    }
    static class Select {
        private final Connect connect;

        public Select(Connect connect) {
            this.connect = connect;
        }

        public void select(String sql) {
            try (Connection connection = connect.connect();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("first_name") + "\t" +
                            resultSet.getString("last_name"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Connect {
        private Connection connection;
        public Connection connect() {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/somedb", "someuser", "somepass");
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return connection;
        }
    }
}
