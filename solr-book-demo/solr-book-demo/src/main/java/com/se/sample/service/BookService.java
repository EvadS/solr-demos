package com.se.sample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.log4j.Logger;

public class BookService {
    private static final Logger logger = Logger.getLogger(BookService.class.getName());

    private final ObjectMapper objectMapper = new ObjectMapper();

    FileService fileService = new FileService();
    public List<Book> convertFromString(String jsonBookList) throws JsonProcessingException {
        List<Book> listCar = objectMapper.readValue(jsonBookList, new TypeReference<List<Book>>(){});
        return listCar;
    }

    public List<Book> readListFromFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;
        int count = 0;
        final InputStream oUserStream  = getClass().getClassLoader().getResourceAsStream(fileName);
        final BufferedReader reader = new BufferedReader (new InputStreamReader(oUserStream));

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
            count++;
        }

        logger.info("read: " + count + " books");
        return convertFromString( content.toString());
    }


}
