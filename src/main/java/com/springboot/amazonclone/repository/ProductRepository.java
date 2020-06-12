package com.springboot.amazonclone.repository;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.springboot.amazonclone.entity.Product;
import io.github.kaiso.relmongo.config.EnableRelMongo;


//Change the parameter type to org.bson.types.ObjectId; from String
@EnableRelMongo
@Repository
public interface ProductRepository extends MongoRepository<Product, String>  {
	

	
	public  List<Product>findByCategoryId(String id);
	Product findProductById(String id);
	Product findProductByName(String name);
	
	//'$options': 'i' = case-insensitive search 
	@Query("{ 'name' : { $regex: ?0,'$options': 'i' } }")
	List<Product> findProductsWithNameLike(String q);
	
	


	//@Query(value="{ 'category.$oid' : ?0 }")
	//@Query("{category:'?0'}")
    //public List<Product> findByCategory(String id);  
	//@Query("{name:'?0'}")
    //Product findByName(String name);
	//@Query(value="{ 'category.$id' : ?0 }")
	//public static   list<Product> void findAllByCategoryId(String id){
	
		
	
	
	

}
