package galewarning.rewardsrestful.util;



import galewarning.rewardsrestful.entity.CustomerEntity;
import galewarning.rewardsrestful.model.Customer;

import java.util.stream.Collectors;

public class CustomerEntityVoConverter {

    public static Customer convertEntityToVo(CustomerEntity entity) {
        if (entity == null) return null;

        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setAge(entity.getAge());
        return customer;
    }

    public static CustomerEntity convertVoToEntity(Customer customer) {
        if (customer == null) return null;

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAge(customer.getAge());
        customerEntity.setName(customer.getName());
        return customerEntity;
    }
}
