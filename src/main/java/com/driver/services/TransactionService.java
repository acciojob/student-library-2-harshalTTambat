package com.driver.services;

import com.driver.Enums.CardStatus;
import com.driver.models.Book;
import com.driver.models.Card;
import com.driver.models.Transaction;
import com.driver.Enums.TransactionStatus;
import com.driver.repositories.BookRepository;
import com.driver.repositories.CardRepository;
import com.driver.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    BookRepository bookRepository5;

    @Autowired
    CardRepository cardRepository5;

    @Autowired
    TransactionRepository transactionRepository5;

    @Value("${books.max_allowed}")
    public int max_allowed_books;

    @Value("${books.max_allowed_days}")
    public int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    public int fine_per_day;

    public String issueBook(int cardId, int bookId) throws Exception {
        //check whether bookId and cardId already exist
        Card card = cardRepository5.findById(cardId).get();
        Book book = bookRepository5.findById(bookId).get();
        String transactionId;
        if (book != null && card != null)
        {
            //conditions required for successful transaction of issue book:
            //1. book is present and available
            //2. card is present and activated
            CardStatus cardStatus = card.getCardStatus();
            if (book.isAvailable()==true && cardStatus.equals("ACTIVATED"))
            {
                //3. number of books issued against the card is strictly less than max_allowed_books
                List<Book> bookList = card.getBooks();
                Transaction transaction = new Transaction();
                if(bookList.size() < max_allowed_books)
                {
                    // If the transaction is successful,
                    transaction.setCard(card);
                    transaction.setBook(book);
                    transaction.setIssueOperation(true);
                    transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);

                    // save the transaction to the list of transactions and return the id
                    List<Transaction> transactionList = book.getTransactions();
                    transactionList.add(transaction);
                    book.setTransactions(transactionList);

                    transactionRepository5.save(transaction);
                    transactionId = transaction.getTransactionId();
                }
                // If it fails:
                else {
                    transaction.setTransactionStatus(TransactionStatus.FAILED);
                    throw new Exception("Book limit has reached for this card");
                }

            }
            // If it fails:
            else throw new Exception("Card is invalid");
        }
        // If it fails:
        else throw new Exception("Book is either unavailable or not present");

        bookRepository5.save(book);
        return transactionId;
    }

    public Transaction returnBook(int cardId, int bookId) throws Exception{

        List<Transaction> transactions = transactionRepository5.find(cardId, bookId, TransactionStatus.SUCCESSFUL, true);
        Transaction transaction = transactions.get(transactions.size() - 1);

        Date date = transaction.getTransactionDate();

        //for the given transaction calculate the fine amount considering the book has been returned exactly when this function is called
        //make the book available for other users
        //make a new transaction for return book which contains the fine amount as well

        Transaction returnBookTransaction  = null;
        return returnBookTransaction; //return the transaction after updating all details
    }
}
