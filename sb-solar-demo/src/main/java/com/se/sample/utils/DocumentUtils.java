package com.se.sample.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.se.sample.config.Config.JSON_EXTENSION;

public class DocumentUtils {

    private DocumentUtils(){

    }

    /**
     * reader solr input document
     * @param targetFolder
     * @param documentId
     * @return
     */
    public static Map<String, Object> readSorlDocumentFromFile(String targetFolder, String documentId) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String inputFileName = documentId + JSON_EXTENSION;
        String filepath = targetFolder + inputFileName;
        Map<String, Object> map = new HashMap<>();
        try(InputStream is = new FileInputStream(new File(filepath))) {
            map = mapper.readValue(is, HashMap.class);
            return map;
        }
    }

}
