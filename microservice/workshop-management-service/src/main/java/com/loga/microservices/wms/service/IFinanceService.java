package com.loga.microservices.wms.service;

import com.loga.microservices.wms.entity.*;

import java.util.Date;
import java.util.List;

public interface IFinanceService {

    Register createFinance(Register register) throws Exception;
    Register readFinance(Long id);
    Register readFinance(Date date);
    List<Register> listFinance(Date debut, Date fin);
    void editFinance(Expense expense, Register register);
    void editFinance(Recipe recipe, Register register);
    void deleteFinance(Long cashflow);
    void deleteRecipe(Long recipe);
    void deleteSpent(Long spent);
    Payment createPayment(Payment payment);
    List<Payment> listPayment();

    Salary createSalary(Salary salary);
    List<Salary> listSalary();
}
