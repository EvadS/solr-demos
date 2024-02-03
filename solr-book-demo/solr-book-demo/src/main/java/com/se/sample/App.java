package com.se.sample;

import com.se.sample.config.BookConfig;
import com.se.sample.model.Book;
import com.se.sample.service.BookService;
import com.se.sample.service.SolrService;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;


public class App {
    final static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) throws IOException, SolrServerException {
        logger.debug("Application started");
        try {

            BookService bookService = new BookService();
            List<Book> bookList = bookService.readListFromFile(BookConfig.BOOK_FILE_NAME);

            SolrService solrService = new SolrService();

          //  solrService.createNewBook(bookList.get(144));

            for(Book item : bookList) {
                int updateStatus = solrService.indexingDocuments(item);
                logger.debug("stored item:" + item.getId()) ;
            }

            int a = 0;
        } catch (ArithmeticException ex) {
            logger.error("Sorry, something wrong!", ex);
        }

        logger.debug("Application finished");
    }
}
