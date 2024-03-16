package ru.fafurin.lesson2;

import java.sql.*;

// Урок 2. SQLite
public class Main {
    // 1. Что такое SQLite?
    // Ответ: SQLite - субд для клиент-серверных приложений, данные которой хранятся в одном файле

    // 2. Напишите запрос для отображения имен (first_name, last_name), используя
    // псевдонимы «Имя», «Фамилия». (Пример таблицы см. таблица 1)
    // Ответ:
    // select first_name AS Имя, last_name AS Фамилия from Users;

    // 3. Напишите запрос и получите все имена из таблицы сотрудников в верхнем
    // регистре (Пример таблицы см. таблица 1)
    // Ответ:
    // select UPPER(first_name), UPPER(last_name) from Users;

    // 4. Напишите запрос и получите все id сотрудников (Пример таблицы см. таблица 1)
    // Ответ:
    // select id from Users;

    // 5. Напишите запрос, чтобы получить первые 3 символа имени из таблицы
    // сотрудников (Пример таблицы см. таблица 1)
    // Ответ:
    // select SUBSTRING(first_name, 1, 3)  from Users;

    // 6. Напишите запрос, чтобы выбрать первые 5 записей из таблицы.
    // Ответ:
    // select * from Users limit 5;




    public static void main(String[] args) {
        Select select = new Select(new Connect("jdbc:sqlite:sqlite.db"));
        select.select("select * from Users;");
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
        private Connection connection = null;
        private final String url;

        public Connect(String url) {
            this.url = url;
        }

        public Connection connect() {
            try {
                connection = DriverManager.getConnection(url);
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return connection;
        }
    }
}
