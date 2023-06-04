package com.loga.microservices.wms.service;

import com.loga.microservices.wms.entity.*;
import com.loga.microservices.wms.repository.PaymentRepository;
import com.loga.microservices.wms.repository.RegisterRepository;
import com.loga.microservices.wms.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FinanceService implements IFinanceService {

    private final RegisterRepository registerRepository;
    private final PaymentRepository paymentRepository;
    private final SalaryRepository salaryRepository;

    @Autowired
    public FinanceService(RegisterRepository registerRepository, PaymentRepository paymentRepository, SalaryRepository salaryRepository) {
        this.registerRepository = registerRepository;
        this.paymentRepository = paymentRepository;
        this.salaryRepository = salaryRepository;
    }

    /**
     * Cette méthode permet d'enregistrer un objet Tresor dans la base de données. Elle retourne l'objet Tresor ainsi créé
     * @param register
     * @return Tresor
     */
    @Override
    @Transactional
    public Register createFinance(Register register) {
        return registerRepository.save(register);
    }

    /**
     * Cette méthode permet de sélectionner un objet Tresor de la base de données
     * @param id
     * @return Tresor
     */
    @Override
    public Register readFinance(Long id) {
        return registerRepository.findById(id).get();
    }

    /**
     * Cette méthode permet de sélectionner un objet Tresor de la base de données
     * @param date
     * @return Tresor
     */
    @Override
    public Register readFinance(Date date) {
        return registerRepository.findByCreatedAt(date);
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Tresor de la base de données à partir dans une collection.
     * @return List
     */
    @Override
    public List<Register> listFinance(Date debut, Date fin) {
        return registerRepository.findAllByCreatedAtBetween(debut,fin);
    }

    @Override
    @Transactional
    public void editFinance(Expense expense, Register register) {
        //todo:edit cashflow
    }

    @Override
    @Transactional
    public void editFinance(Recipe recipe, Register register) {
        //todo:edit cashflow
    }

    @Override
    public void deleteFinance(Long cashflow) {
        //todo: delete cashflow
    }

    @Override
    public void deleteRecipe(Long recipe) {
        //todo: delete recipe
    }

    @Override
    public void deleteSpent(Long spent) {
        //todo : delete spent
    }

    /**
     * Cette méthode permet d'enregistrer un objet Versement dans la base de données
     * @param bill
     * @return
     */
    @Override
    @Transactional
    public Payment createPayment(Payment bill) {
        return paymentRepository.save(bill);
    }

    /**
     * Cette méthode permet de lister les objets Versement de la base de données
     * @return List
     */
    @Override
    public List<Payment> listPayment() {
        return paymentRepository.findAll();
    }

    /**
     * Cette méthode permet d'enregistrer un objet Salaire dans la base de données.
     * @param salary
     * @return Salaire
     */
    @Override
    @Transactional
    public Salary createSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Salaire de la base de données
     * @return List
     */
    @Override
    public List<Salary> listSalary() {
        return salaryRepository.findAll();
    }

}
