package Bank.jpamany.controller;

import Bank.jpamany.entity.Account;
import Bank.jpamany.entity.Address;
import Bank.jpamany.entity.Customer;
import Bank.jpamany.service.AccountService;
import Bank.jpamany.service.CustomerService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
@Validated
public class AccountController {

    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccountByAccountId(@Positive @PathVariable int id) {
        return accountService.find(id);
    }

    //CustomerIdye göre ekleme ve güncelleme olacağı için ayrıca customerService de dependency injection yapıldı!!!
    @PostMapping("/{customerId}")
    public Account addAccountByCustomerId(@RequestBody Account account, @PathVariable int customerId) {
        Customer customerToAddAccount = customerService.find(customerId);
        customerToAddAccount.add(account);//Customer Entity içinden çağrılan add() methodu
        account.setCustomer(customerToAddAccount);//Yukarıda customera accountu, burada da accounta customerı ekledik.(Bidirectional!)
        return accountService.save(account);
    }

    @PutMapping("/{customerId}")
    public Account updateAccount(@RequestBody Account account, @PathVariable int customerId) {
        Customer customerToUpdateAccount = customerService.find(customerId);

      //1. yöntem stream() ile
      /*  Account accountToUpdate = customerToUpdateAccount.getAccountList().stream().filter(account1 ->
                account1.getId() == customerToUpdateAccount.getId()).collect(Collectors.toList()).get(0);*/

      //2. foreach ile
        Account accountToUpdate = null;
        for(Account account1: customerToUpdateAccount.getAccountList()){
            if(account1.getId()==account.getId()){
                accountToUpdate=account1;
            }
        }

        //Aşağıdaki kısımlar her 2 yöntemde de değişmiyor!
        if(accountToUpdate==null){
            //throw exception
        }

        int index = customerToUpdateAccount.getAccountList().indexOf(accountToUpdate);
        customerToUpdateAccount.getAccountList().set(index, account);

        account.setCustomer(customerToUpdateAccount);
        return accountService.save(account);
    }

    @DeleteMapping("/{id}")
    public Account deleteAccount(@PathVariable int id) {
        Account accountToDelete = getAccountByAccountId(id);
        accountService.delete(accountToDelete);
        return accountToDelete;
    }
}