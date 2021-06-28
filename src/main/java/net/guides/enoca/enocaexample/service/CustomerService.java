package net.guides.enoca.enocaexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.enoca.enocaexample.model.Customer;
import net.guides.enoca.enocaexample.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository CustomerRepository;

    public List<Customer> findAll() {
        return CustomerRepository.findAll();
    }
    public Optional<Customer> findById(long id) {
        return CustomerRepository.findById(id);
    }
    public Customer createOrUpdate(Customer Customer) {
        return CustomerRepository.save(Customer);
    }
    public void deleteById(long id) {
        CustomerRepository.deleteById(id);
    }
}
