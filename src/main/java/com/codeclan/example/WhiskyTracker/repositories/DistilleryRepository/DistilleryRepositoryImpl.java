package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;


public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

        public List<Distillery> findWhiskeyFromDistilleryRegion(String region){
        List<Distillery> results = null;
        Criteria cr = null;

        try {
            Session session = entityManager.unwrap(Session.class);
            cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery", "distillery");
            cr.add(Restrictions.eq("distillery.region", region));
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        results = cr.list();
        return results;
    }

    public List<Distillery> findWhiskeyFromDistilleryOfAge(int age){
            List<Distillery> results = null;
            Criteria cr = null;

        try {
            Session session = entityManager.unwrap(Session.class);
            cr = session.createCriteria(Distillery.class);
            cr.createAlias("whiskies", "whisky");
            cr.add(Restrictions.eq("whisky.age", age));
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        results = cr.list();
        return results;
    }
}







