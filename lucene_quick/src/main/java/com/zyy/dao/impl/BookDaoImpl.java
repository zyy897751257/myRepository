package com.zyy.dao.impl;

import com.zyy.dao.BookDao;
import com.zyy.domain.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zyy
 * @date: 2019-06-16 09:25
 * @description:
 * @version:
 */
public class BookDaoImpl implements BookDao {

    @Override
    public List<Book> queryBookList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Book> bookList = new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///maven", "root","123" );
            String sql = "select * from book";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setPrice(resultSet.getFloat("price"));
                book.setPic(resultSet.getString("pic"));
                book.setDesc(resultSet.getString("desc"));
                bookList.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookList;
    }
}
