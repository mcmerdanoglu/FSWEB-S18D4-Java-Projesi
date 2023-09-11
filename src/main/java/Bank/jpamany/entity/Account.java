package Bank.jpamany.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="account", schema = "springbankjpa")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="account_name")
    private String accountName;

    @Column(name="money_amount")
    private double moneyAmount;

    @ManyToOne (cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    //Çok olan account tek olan customer olduğu için account classına ManyToOne yazdık!!!
    @JoinColumn(name="customer_id") //foreign key
    private Customer customer;
}
