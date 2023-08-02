package com.se.sample.util;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.se.sample.model.Book;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class BookUtils {

    private static  final Logger logger = LoggerFactory.getLogger(BookUtils.class);


    public static Book BuildRandomBook (){

        Faker faker = new Faker( new Locale("ru-RU"));

        Book book = new Book(UUID.randomUUID().toString(),
                faker.code().isbn10(),
                faker.book().author(),
                faker.book().title(),
                faker.book().publisher(),
                faker.book().genre(),
                "is useful to do development and also validation with dummy data");

        logger.debug("Generated book: {}", book);
        return book;
    }


    public static List<Book> buildBookList(int count ){
        List<Book> bookList = new ArrayList<>();

        for(var i =0; i<  count; i++){
            bookList.add(BuildRandomBook());
        }

        return bookList;
    }

    public static String buildBookJson (int count ){
        List<Book> bookList = buildBookList(count);

        Gson gson = new Gson();
        String jsonArray = gson.toJson(bookList);

        logger.info("---------------------------");
        logger.info(jsonArray);
        return jsonArray;
    }

    @Test
    public void buildBookShouldWorkCorrect(){
        buildBookJson(10);
    }
}
