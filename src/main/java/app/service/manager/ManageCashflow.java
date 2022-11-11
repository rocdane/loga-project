package app.service.manager;

import app.dao.BillingRepository;
import app.dao.FinanceRepository;
import app.dao.SalaryRepository;
import app.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ManageCashflow implements IManageCashflow{

    private final FinanceRepository financeRepository;
    private final BillingRepository billingRepository;
    private final SalaryRepository salaryRepository;
    /**
     * Cette méthode permet d'enregistrer un objet Tresor dans la base de données. Elle retourne l'objet Tresor ainsi créé
     * @param finance
     * @return Tresor
     */
    @Override
    public Finance createCashflow(Finance finance) {
        return financeRepository.save(finance);
    }

    /**
     * Cette méthode permet de sélectionner un objet Tresor de la base de données
     * @param id
     * @return Tresor
     */
    @Override
    public Finance readCashflow(Long id) {
        return financeRepository.findById(id).get();
    }

    /**
     * Cette méthode permet de sélectionner un objet Tresor de la base de données
     * @param date
     * @return Tresor
     */
    @Override
    public Finance readCashflow(Date date) {
        return financeRepository.findByDate(date);
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Tresor de la base de données à partir dans une collection.
     * @return List
     */
    @Override
    public List<Finance> listCashflow(Date debut, Date fin) {
        return financeRepository.findAllByDateBetween(debut,fin);
    }

    @Override
    public void editCashflow(Spent spent, Finance finance) {
        //todo:edit cashflow
    }

    @Override
    public void editCashflow(Recipe recipe, Finance finance) {
        //todo:edit cashflow
    }

    @Override
    public void deleteCashflow(Long cashflow) {
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
     * @param billing
     * @return
     */
    @Override
    public Billing createBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    /**
     * Cette méthode permet de lister les objets Versement de la base de données
     * @return List
     */
    @Override
    public List<Billing> listBilling() {
        return billingRepository.findAll();
    }

    /**
     * Cette méthode permet d'enregistrer un objet Salaire dans la base de données.
     * @param salary
     * @return Salaire
     */
    @Override
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
