package com.example.batchInsert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaNoBatchInsertsIntegrationTest {
	
	@PersistenceContext
    private EntityManager entityManager;

}
