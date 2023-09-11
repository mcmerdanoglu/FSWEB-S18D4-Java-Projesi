package Bank.jpamany.service;

import Bank.jpamany.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address find(int id);

    Address save(Address adress);

    void delete(Address address);
}
