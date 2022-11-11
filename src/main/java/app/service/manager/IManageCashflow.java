package app.service.manager;

import app.model.*;

import java.util.Date;
import java.util.List;

public interface IManageCashflow {

    Finance createCashflow(Finance finance) throws Exception;
    Finance readCashflow(Long id);
    Finance readCashflow(Date date);
    List<Finance> listCashflow(Date debut, Date fin);
    void editCashflow(Spent spent, Finance finance);
    void editCashflow(Recipe recipe, Finance finance);
    void deleteCashflow(Long cashflow);
    void deleteRecipe(Long recipe);
    void deleteSpent(Long spent);
    Billing createBilling(Billing billing);
    List<Billing> listBilling();

    Salary createSalary(Salary salary);
    List<Salary> listSalary();
}
