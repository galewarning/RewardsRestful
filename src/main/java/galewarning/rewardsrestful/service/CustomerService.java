package galewarning.rewardsrestful.service;

import galewarning.rewardsrestful.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();
    Customer findById(long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomerById(long id);
}
