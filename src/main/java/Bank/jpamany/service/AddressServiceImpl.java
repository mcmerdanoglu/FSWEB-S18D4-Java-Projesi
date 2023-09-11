package Bank.jpamany.service;

import Bank.jpamany.dao.AddressRepository;
import Bank.jpamany.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address find(int id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            return address.get();
        }
        return null;
    }

    @Override
    public Address save(Address adress) {
        return addressRepository.save(adress);
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }
}
