package edu.lionid.clientv2.service;

import edu.lionid.clientv2.MainApplication;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ClientProperties {
    private final Properties properties = new Properties();

    private String allBook;
    private String findByIdBook;
    private String saveBook;
    private String updateBook;
    private String findByAuthorInBook;
    private String findByTitleInBook;
    private String allCity;
    private String findByIdCity;
    private String saveCity;
    private String updateCity;
    private String allGenre;
    private String findByIdGenre;
    private String saveGenre;
    private String updateGenre;
    private String allPublisher;
    private String findByIdPublisher;
    private String savePublisher;
    private String updatePublisher;
    private String deleteBook;
    private String deleteCity;
    private String deleteGenre;
    private String deletePublisher;

    private String findByIdAuthor;
    private String allAuthor;
    private String saveAuthor;
    private String updateAuthor;
    private String deleteAuthor;

    public ClientProperties() {
        try (InputStream input = MainApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(input);
            properties.load(input);
            allAuthor = properties.getProperty("author.getAll");
            deleteAuthor = properties.getProperty("author.del");
            findByIdAuthor = properties.getProperty("author.findById");
            saveAuthor = properties.getProperty("author.save");
            updateAuthor = properties.getProperty("author.update");
            allBook = properties.getProperty("book.getAll");
            deleteBook = properties.getProperty("book.del");
            findByIdBook = properties.getProperty("book.findById");
            findByAuthorInBook = properties.getProperty("book.findByAuthorInBook");
            findByTitleInBook = properties.getProperty("book.findByTitleInBook");
            saveBook = properties.getProperty("book.save");
            updateBook = properties.getProperty("book.update");
            allCity = properties.getProperty("city.getAll");
            deleteCity = properties.getProperty("city.del");
            findByIdCity = properties.getProperty("city.findById");
            saveCity = properties.getProperty("city.save");
            updateCity = properties.getProperty("city.update");
            allGenre = properties.getProperty("genre.getAll");
            deleteGenre = properties.getProperty("genre.del");
            findByIdGenre = properties.getProperty("genre.findById");
            saveGenre = properties.getProperty("genre.save");
            updateGenre = properties.getProperty("genre.update");
            allPublisher = properties.getProperty("publisher.getAll");
            deletePublisher = properties.getProperty("publisher.del");
            findByIdPublisher = properties.getProperty("publisher.findById");
            savePublisher = properties.getProperty("publisher.save");
            updatePublisher = properties.getProperty("publisher.update");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}