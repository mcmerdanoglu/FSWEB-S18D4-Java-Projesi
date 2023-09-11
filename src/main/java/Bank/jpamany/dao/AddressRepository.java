package Bank.jpamany.dao;

import Bank.jpamany.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address/*Object*/, Integer/*primary key*/> {

}
