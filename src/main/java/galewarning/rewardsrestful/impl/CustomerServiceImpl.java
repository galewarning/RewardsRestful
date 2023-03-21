package galewarning.rewardsrestful.impl;

import galewarning.rewardsrestful.dao.CustomerRepository;
import galewarning.rewardsrestful.entity.CustomerEntity;
import galewarning.rewardsrestful.model.Customer;
import galewarning.rewardsrestful.service.CustomerService;
import galewarning.rewardsrestful.util.CustomerEntityVoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream().map(e -> CustomerEntityVoConverter.convertEntityToVo(e)).collect(Collectors.toList());
    }

    @Override
    public Customer findById(long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        return CustomerEntityVoConverter.convertEntityToVo(customerEntity);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepository.saveAndFlush(CustomerEntityVoConverter
                        .convertVoToEntity(customer));
        return CustomerEntityVoConverter.convertEntityToVo(customerEntity);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepository.saveAndFlush(CustomerEntityVoConverter
                .convertVoToEntity(customer));
        return CustomerEntityVoConverter.convertEntityToVo(customerEntity);
    }

    @Override
    public void deleteCustomerById(long id) {
        customerRepository.deleteById(id);
    }
}
