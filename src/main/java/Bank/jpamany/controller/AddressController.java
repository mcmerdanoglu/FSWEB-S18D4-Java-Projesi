package Bank.jpamany.controller;

import Bank.jpamany.entity.Address;
import Bank.jpamany.service.AddressService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@Validated
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public List<Address> getAllAddresses(){
    return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address getAddressByAddressId(@Positive @PathVariable int id){
        return addressService.find(id);
    }

    @PostMapping("/")
    public Address addAddress(@RequestBody Address address){
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@RequestBody Address address, @PathVariable int id) {
        Address addressToUpdate = getAddressByAddressId(id);
        if (addressToUpdate != null) {
            address.setId(id);
            return addressService.save(address);
        }
            return null;
    }

    @DeleteMapping("/{id}")
    public Address deleteAddress(@PathVariable int id){
    Address addressToDelete = getAddressByAddressId(id);
    addressService.delete(addressToDelete);
    return addressToDelete;
    }
}
