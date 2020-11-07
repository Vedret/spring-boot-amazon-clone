# Spring-boot-amazon-clone with Spring security + Bootstrap + Thymeleaf +Mongodb on Atlas + Angular Cart + Algolia search + Sripe payment + Heroku PaaS ...

A sample application which demonstrates an E-commerce website using the Java Spring Boot and [Bootstrap](https://getbootstrap.com). The application loads products, users in a MongoDB database and displays them. Users can select to display products in a different category. Users can click on any product to get more information including pricing, reviews and rating. Users can select items and add them to their shopping cart and pay.
 
Application implements [Algolia search](https://www.algolia.com/doc/) interface which contain a search bar with query suggestions. These help your users find what they are looking for and discover new products. For your busines can help promote specific products or brands, test different search configurations, get insights and analytics on user behavior, get insights and analytics on your products.

Payment API  is [Stripe](https://stripe.com/en-de). Stripe is a suite of payment APIs that powers commerce for online businesses of all sizes, including fraud prevention, and subscription management.


The application is depoloyed on Heroku PaaS and can be tested here https://spring-boot-amazon-clone.herokuapp.com

## Prerequisities
For security reason repository is missing application.properties file with connection strings to MongoDb and Algolia search.
Edit your application.properties, add:

```javascript
    spring.data.mongodb.uri=mongodb+srv://user:password@databsename.it9xb.mongodb.net/databasename?retryWrites=true&w=majority
    spring.session.store-type=mongodb
    server.port=8080
    algolia.YourApplicationID=xxxxxx
    algolia.YourAdminAPIKey=xxxxxx
```

## Tech Stack
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring security](https://spring.io/projects/spring-security)
* [Bootstrap](https://getbootstrap.com)
* [Thymeleaf](https://www.thymeleaf.org)
* [Mongodb on Atlas](https://www.mongodb.com/atlas)
* [RelMongo Java relationship-enabled domain model persistence framework for MongoDB](https://kaiso.github.io/relmongo/)
* [Angular Cart](https://angular.io) 
* [Algolia search](https://www.algolia.com/doc/) 
* [Sripe payment](https://stripe.com/en-de) 
* [Heroku PaaS](https://www.heroku.com)
