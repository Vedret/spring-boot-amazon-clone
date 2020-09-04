package com.springboot.amazonclone.repository.implementation;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.springboot.amazonclone.entity.Category;
import com.springboot.amazonclone.entity.Product;
import com.springboot.amazonclone.repository.AlgoliaRepository;
import com.springboot.amazonclone.repository.CategoryRepository;
import com.springboot.amazonclone.repository.ProductRepository;

import io.github.kaiso.relmongo.config.EnableRelMongo;
@EnableRelMongo
@Repository
public class AlgoliaSearchImpl implements AlgoliaRepository {
	
	@Autowired
	ProductRepository productRepository ;
	
	@Autowired
	CategoryRepository categoryRepository ;
	
	
	//Read keys from application.properties
	@Autowired
	private Environment env;
	

	public void initAlgoliaIndex(Product product) throws IOException{
		String YourApplicationID = env.getProperty("algolia.YourApplicationID");
		String YourAdminAPIKey = env.getProperty("algolia.YourAdminAPIKey");
		
		// Create a SearchClient (it's a Closable, so you can leverage the try-with-resources
	    // construction
	    // to let the JVM close underlying resources when appropriate)
		
	    try (SearchClient searchClient =
	        DefaultSearchClient.create(YourApplicationID, YourAdminAPIKey)) 
	    {

	      // Init an index on algolia, but first manually create one at Algolia site
	      SearchIndex<Product> index =
	        searchClient.initIndex("products" , Product.class);

	      Category category = categoryRepository.findCategoryById(product.getCategory().getId());
	      product.setCategory(category);
	      index.saveObject(product, true);
	     }
   
	}

}
