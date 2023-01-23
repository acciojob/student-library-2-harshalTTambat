package com.driver.services;

import com.driver.models.Book;
import com.driver.models.Student;
import com.driver.models.Card;
import com.driver.Enums.CardStatus;
import com.driver.repositories.CardRepository;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository3;
    @Autowired
    StudentRepository studentRepository;

    public Card createAndReturn(Student student){
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        //link student with a new card
        card.setStudent(student);
        List<Book> bookList = new ArrayList<>();
        card.setBooks(bookList);
        return card;
    }

    public void deactivateCard(int student_id){
        cardRepository3.deactivateCard(student_id, CardStatus.DEACTIVATED.toString());
        Student student = studentRepository.findById(student_id).get();
        Card card = student.getCard();
        cardRepository3.delete(card);
    }
}
