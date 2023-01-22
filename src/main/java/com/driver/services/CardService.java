package com.driver.services;

import com.driver.models.Book;
import com.driver.models.Student;
import com.driver.models.Card;
import com.driver.Enums.CardStatus;
import com.driver.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {


    @Autowired
    CardRepository cardRepository3;

    public Card createAndReturn(Student student){
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        //link student with a new card
        card.setStudent(student);
        cardRepository3.save(card);
        return card;
    }

    public void deactivateCard(int student_id){
        cardRepository3.deactivateCard(student_id, CardStatus.DEACTIVATED.toString());
    }
}
