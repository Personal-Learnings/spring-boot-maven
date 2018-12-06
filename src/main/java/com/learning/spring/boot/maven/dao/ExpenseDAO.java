package com.learning.spring.boot.maven.dao;

import com.learning.spring.boot.maven.vo.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ExpenseDAO {

    private JdbcTemplate jdbcTemplate;

    public ExpenseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createExpense(Expense expense) {
        return jdbcTemplate.update("INSERT INTO EXPENSE VALUES (?, ?, ?)", expense.getId(), expense.getName(), expense.getDescription());
    }

    public List<Expense> getExpense() {

        List<Expense> expenseList = null;
        List<Map<String, Object>> resultSetList = jdbcTemplate.queryForList("SELECT * FROM EXPENSE");

        if(null != resultSetList) {
            expenseList = resultSetList.stream().map(p-> {
                Expense expense = new Expense();
                expense.setName(String.valueOf(p.get("name")));
                expense.setDescription(String.valueOf(p.get("description")));
                expense.setId(Integer.parseInt(String.valueOf(p.get("id"))));
                return expense;
            }).collect(Collectors.toList());
        }
        return expenseList;
    }

    public int updateExpense(Expense expense) {
        return jdbcTemplate.update("UPDATE EXPENSE SET name=?, description=? where id=?", expense.getName(), expense.getDescription(), expense.getId());
    }
}