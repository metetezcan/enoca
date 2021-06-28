package net.guides.enoca.enocaexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.guides.enoca.enocaexample.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}