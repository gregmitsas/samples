package com.gregmitsas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gregmitsas.model.Customer;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
}
