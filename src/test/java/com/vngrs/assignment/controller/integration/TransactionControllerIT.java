package com.vngrs.assignment.controller.integration;

import com.vngrs.assignment.controller.TransactionController;
import com.vngrs.assignment.model.Appointment;
import com.vngrs.assignment.model.Transaction;
import com.vngrs.assignment.repository.TransactionRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerIT {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionController transactionController;

    @After
    public void tearDown() {
        transactionRepository.deleteAll();
    }

    @Test
    public void testGetAllTransactions() {

        assertEquals(transactionRepository.findAll().size(), 0);

        Transaction transaction = Transaction.builder().transactionId("8027fef945a417gdda5379f3")
                .cancellationFee(150.0)
                .appointment(Appointment.builder().build())
                .build();

        assertEquals(transaction, transactionRepository.save(transaction));
        assertEquals(transactionRepository.findAll().size(), 1);
        assertEquals(1, transactionController.getAllTransactions().size());

    }

    @Test
    public void testDeleteTransactions() {

        Transaction transaction = Transaction.builder().transactionId("8027fef945a417gdda5379f3")
                .cancellationFee(150.0)
                .appointment(Appointment.builder().build())
                .build();

        assertEquals(transaction, transactionRepository.save(transaction));
        assertEquals(transactionRepository.findAll().size(), 1);

        transactionController.deleteTransactions();
        assertEquals(0, transactionRepository.findAll().size());
    }
}
