package net.guides.enoca.enocaexample.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.enoca.enocaexample.elasticsearch.ElasticSearchBinded;
import net.guides.enoca.enocaexample.model.Customer;
import net.guides.enoca.enocaexample.service.CustomerService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
    	//System.out.println("metetemtetemtemtemtemtemtme");
    	ResponseEntity<Customer> temp = ResponseEntity.ok(customerService.createOrUpdate(customer));
    	ElasticSearchBinded.elasticSearch(customer);
    	return temp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable long id) {

        Optional<Customer> customer = customerService.findById(id);

        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer.get());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Customer> update(@PathVariable long id, @Valid @RequestBody Customer customer2) {

        Optional<Customer> customer = customerService.findById(id);

        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        //customer.get().setName(customer2.getName());
        //customer.get().setSurname(customer2.getSurname());
        //customer.get().setAddress(customer2.getAddress());
        //customer.get().setPhone(customer2.getPhone());
		final Customer updatedCustomer = customerService.createOrUpdate(customer.get());
		return ResponseEntity.ok(updatedCustomer);

       // return ResponseEntity.ok(customerService.createOrUpdate(customer.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        Optional<Customer> p = customerService.findById(id);

        if (!p.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
