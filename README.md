# Rewards Restful Project

    A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
    A customer receives 2 points for every dollar spent over $100 in each transaction,
     plus 1 point for every dollar spent over $50 in each transaction (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

    Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.

    Produce Restful APIs for get customer's reward points. By customized input like user-id, user-name.
    Use common sense to make assumptions.
    Make up a data set to best demonstrate your solution

    basic function (required):
    - CRUD operation: get/post/put/delete for transaction and customer, get awarding points
    - Exception Handling
    - .gitignore
    - validation
    - unit test: junit/Mockito

    bonus (optional)
    - front end (react, angular, vue, pure javascript)
    - Authentication/ Authorization
    - AWS (try to use some AWS Service)
    - cache
    - Async
    - ...


## how to run the project
A dataset is preloaded to h2 database by script.sql when the application starts. 
The server is running on:
    
    http://localhost:8080
    
API for customers

    GET     /api/customers              get all customers
    GET     /api/customers/{id}         get customer by id
    POST    /api/customers              create a customer
    PUT     /api/customers/{id}         update a customer
    DELETE  /api/customers/{id}         delete a customer

API for transaction

    GET     /api/transactions              get all transactions
    GET     /api/transactions/{id}         get transaction by id
    POST    /api/transactions              create a transaction
    PUT     /api/transactions/{id}         update a transaction
    DELETE  /api/transactions/{id}         delete a transaction

API for rewards

    GET     /api/rewards/{customerId}                       get rewards for each month and total
    GET     /api/transactions/firstmonth/{customerId}       get first month transaction
    GET     /api/transactions/secondmonth/{customerId}      get second month transaction
    GET     /api/transactions/thirdmonth/{customerId}       get third month transaction
    GET     /api/rewards/firstmonth/{customerId}            get first month rewards
    GET     /api/rewards/secondmonth/{customerId}           get second month rewards
    GET     /api/rewards/thirdmonth/{customerId}            get third month rewards

Exception handling

    CustomerNotFoundException
    TransactionNotFoundException

Validation
    
    @Min, @Max for customer age
    @NotBlank for customer name
    @Min for transaction amount