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
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zyy
 * @date: 2019-06-17 08:46
 * @description:
 * @version:
 */
public class TestIKAnalyzer {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void Test02() throws IOException {
        List<Book> bookList = bookDao.queryBookList();
        List<Document> documents = new ArrayList();

        IKAnalyzer analyzer = new IKAnalyzer();
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
