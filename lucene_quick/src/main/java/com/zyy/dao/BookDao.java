package com.zyy.dao;

import com.zyy.domain.Book;

import java.util.List;

/**
 * @author: Zyy
 * @date: 2019-06-16 09:24
 * @description:
 * @version:
 */
public interface BookDao {

    List<Book> queryBookList();
}
