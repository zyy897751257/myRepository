package com.zyy.test;

import com.zyy.dao.BookDao;
import com.zyy.dao.impl.BookDaoImpl;
import com.zyy.domain.Book;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zyy
 * @date: 2019-06-16 11:13
 * @description:
 * @version:
 */
public class TestCreateIndex {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void Test01() throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        FSDirectory directory = FSDirectory.open(new File("F:/index").toPath());
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        //创建文档对象
        Document doc = new Document();
        doc.add(new TextField("id",19999+"", Field.Store.YES));
        doc.add(new TextField("name","小红", Field.Store.YES));
        doc.add(new TextField("price",19.9+"", Field.Store.YES));
        doc.add(new TextField("pic","http://1.jpg", Field.Store.YES));
        doc.add(new TextField("desc","这是一本描述idea使用的书", Field.Store.YES));

        indexWriter.addDocument(doc);

        indexWriter.commit();
        indexWriter.close();
    }

    @Test
    public void Test02() throws IOException {
        List<Book> bookList = bookDao.queryBookList();
        List<Document> documents = new ArrayList<>();

        StandardAnalyzer analyzer = new StandardAnalyzer();
        FSDirectory directory = FSDirectory.open(new File("F:/index").toPath());
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        for (Book book : bookList) {
            Document doc = new Document();
            doc.add(new TextField("id",book.getId()+"", Field.Store.YES));
            doc.add(new TextField("name",book.getName(), Field.Store.YES));
            doc.add(new TextField("price",book.getPrice()+"", Field.Store.YES));
            doc.add(new TextField("pic",book.getPic(), Field.Store.YES));
            doc.add(new TextField("desc",book.getDesc(), Field.Store.YES));
            documents.add(doc);
        }

        indexWriter.addDocuments(documents);

        indexWriter.commit();
        indexWriter.close();
    }
}
