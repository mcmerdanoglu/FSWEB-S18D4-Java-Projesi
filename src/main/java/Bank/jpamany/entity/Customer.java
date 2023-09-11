package Bank.jpamany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer", schema = "springbankjpa")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private double salary;

    @OneToOne(cascade = CascadeType.ALL)//1 customer silinince ona bağlı adresinde silinmesi için!!!
    @JoinColumn(name = "address_id") //foreign key
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    //Account classında customer olarak işaretlendin manasında.(customer_id in account foreign key)
    private List<Account> accountList;

    //AccountControllerdaki addAccountByCustomerId methodu için ekstra yazılan add methodu!
    public void add(Account account){
        if(accountList == null){
            accountList = new ArrayList<>();
        }
        accountList.add(account);
    }
}
