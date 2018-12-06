package com.learning.spring.boot.maven.controller;

import com.learning.spring.boot.maven.dao.ExpenseDAO;
import com.learning.spring.boot.maven.vo.Expense;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    private ExpenseDAO expenseDAO;

    public ExpenseController(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Hello";
    }

    @GetMapping("/expense")
    public Expense getExpense() {
        Expense expense = new Expense();
        expense.setName("Rent");
        expense.setDescription("December Month Rent");
        return expense;
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getExpenses() {
        return new ResponseEntity<>(expenseDAO.getExpense(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createExpenses(@RequestBody Expense expense) {
        return new ResponseEntity<>(expenseDAO.createExpense(expense), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> updateExpenses(@RequestBody Expense expense) {
        return new ResponseEntity<>(expenseDAO.updateExpense(expense), HttpStatus.OK);
    }
}