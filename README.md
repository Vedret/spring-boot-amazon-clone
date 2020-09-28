# spring-boot-amazon-clone with Spring security + Bootstrap + Thymeleaf +Mongodb on Atlas + Angular Cart + Algolia search ...
## Test it here  https://spring-boot-amazon-clone.herokuapp.com

Please note that repository is missing application.properties file with connection strings to MongoDb and Algolia search.
Edit your application.properties add:

> spring.data.mongodb.uri=mongodb+srv://user:password@databsename.it9xb.mongodb.net/databasename?retryWrites=true&w=majority

> spring.session.store-type=mongodb
> server.port=8080

> algolia.YourApplicationID=xxxxxx
> algolia.YourAdminAPIKey=xxxxxx
