package com.zyy.test;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author: Zyy
 * @date: 2019-06-16 20:32
 * @description:
 * @version:
 */
public class TestSearchIndex {

    @Test
    public void Tes01() throws Exception {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        //默认搜索desc字段
        QueryParser queryParser = new QueryParser("desc", analyzer);
        Query query = queryParser.parse("java");

        FSDirectory directory = FSDirectory.open(new File("F:/index").toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        TopDocs docs = indexSearcher.search(query, 10);
        System.out.println("总记录数:" + docs.totalHits);

        ScoreDoc[] scoreDocs = docs.scoreDocs;
        for (ScoreDoc doc : scoreDocs) {
            System.out.println("评分:" + doc.score);
            System.out.println("唯一编号:" + doc.doc);
            Document document = indexSearcher.doc(doc.doc);
            System.out.println("bookId:" + document.get("id"));
            System.out.println("name:" + document.get("name"));
            System.out.println("pic:" + document.get("pic"));
            System.out.println("price:" + document.get("price"));
            //System.out.println("desc:" + document.get("desc"));
            System.out.println("---------------------------；");
        }

        indexReader.close();
    }
}
