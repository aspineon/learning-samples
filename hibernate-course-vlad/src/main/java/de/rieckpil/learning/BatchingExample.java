package de.rieckpil.learning;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import de.rieckpil.learning.domain.Orders;

@Service
public class BatchingExample implements CommandLineRunner {

	@Autowired
	private EntityManager em;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		this.em.unwrap(Session.class).setJdbcBatchSize(20);

		for (int i = 0; i < 20; i++) {
			this.em.persist(new Orders(UUID.randomUUID().toString()));
		}

	}

}
