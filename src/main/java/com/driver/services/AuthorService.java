package com.driver.services;

import com.driver.models.Author;
import com.driver.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {


    @Autowired
    AuthorRepository authorRepository1;

    public String create(Author author){
        try
        {
            authorRepository1.save(author);
        }
        catch (Exception e)
        {
            return "sorry..can not create Author ";
        }
        return "Author added successfully..";
    }
}
