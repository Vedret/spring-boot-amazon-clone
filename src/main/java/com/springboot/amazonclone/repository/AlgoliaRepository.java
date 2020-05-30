package com.springboot.amazonclone.repository;

import java.io.IOException;

import com.springboot.amazonclone.entity.Product;


public interface AlgoliaRepository {
	
	void initAlgoliaIndex (Product product) throws IOException;

}
