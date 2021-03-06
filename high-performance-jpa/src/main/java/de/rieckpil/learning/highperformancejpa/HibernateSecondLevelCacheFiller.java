package de.rieckpil.learning.highperformancejpa;

import de.rieckpil.learning.highperformancejpa.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Profile("secondlevel")
@Transactional
@Slf4j
public class HibernateSecondLevelCacheFiller implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) {

        Person p1 = new Person();
        p1.setName("Phil");
        
        Person p2 = new Person();
        p2.setName("Phil");
        
        Person p3 = new Person();
        p3.setName("Phil");
       
        Person p4 = new Person();
        p4.setName("Phil");
        
        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(p3);
        entityManager.persist(p4);
        entityManager.flush();

        Person personFromDb = entityManager.find(Person.class, 1L);

        System.out.println("personFromDb = " + personFromDb);

        String region = Person.class.getName();

        SecondLevelCacheStatistics statistics = entityManager.unwrap(Session.class).getSessionFactory().getStatistics
                ().getSecondLevelCacheStatistics(region);

        log.info("\nRegion: {}, \nStatistic: {}, \nEntries: {}", region, statistics, statistics.getEntries());

    }
}
