package Bank.jpamany.service;

import Bank.jpamany.entity.Account;
import Bank.jpamany.entity.Address;
import Bank.jpamany.entity.Customer;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account find(int id);

    Account save(Account account);

    void delete(Account account);
}
