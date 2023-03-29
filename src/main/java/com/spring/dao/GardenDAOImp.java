package com.spring.dao;

import com.spring.model.Garden;
import com.spring.model.GardenProduct;
import com.spring.model.Product;
import com.spring.service.ProductService;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by oguzhanaslan on 24.09.2020.
 */

@Repository
public class GardenDAOImp implements GardenDAO {

    @Setter
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Object> findAllgarden(int userID) {


        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map( g.gardenID as gardenID,u.userID as userID, g.gardenName as gardenName, g.ada as ada, g.parsel as parsel, g.city as city, g.town as town, " +
                            "g.district as district, g.area as area) from Garden g inner join  g.user u where u.userID =:userID  ");


            query.setParameter("userID", userID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }

    }

    @Override
    public List<Object> listGardencost(int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map(sum(c.fuel+c.seed+c.pesticide+c.chemicalfertilizers+c.organicfertilizers+c.irrigation+c.laborforce+c.machine) as toplam_masraf)from Cost c inner join c.garden g  where  g.gardenID=:gardenID ");
            query.setParameter("gardenID", gardenID);
            List gardenlist = query.getResultList();
            transaction.commit();
            return gardenlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Object> listGardenproduct(int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map ( p.productName as productName ) from Garden g inner join g.product p where  g.gardenID=:gardenID ");

            query.setParameter("gardenID", gardenID);
            List gardenlist = query.getResultList();
            transaction.commit();
            return gardenlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }


    @Override
    public void saveGarden(Garden garden) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(garden);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }


    @Override
    public List<Garden> getGarden(int userID) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map( g.gardenName as gardenName, g.gardenID as gardenID  ) from Garden g inner join  g.user u where u.userID =:userID  ");


            query.setParameter("userID", userID);
            List<Garden> gardens = (List<Garden>) query.getResultList();
            transaction.commit();
            return gardens;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Product> getGardenproduct(int gardenID, int userID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select distinct new Map(gp.product.productName as productName, gp.product.productID as productID) from GardenProduct gp inner join gp.garden g  inner join gp.user u where g.gardenID=:gardenID and u.userID =:userID  ");

            query.setParameter("gardenID", gardenID);
            query.setParameter("userID", userID);
            List<Product> products = (List<Product>) query.getResultList();
            transaction.commit();
            return products;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public void saveGardenproduct(GardenProduct gardenProduct) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(gardenProduct);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @Override
    public void gardenupdate(int userID,int ada, int area, int parsel, String gardenName, String city, String town, String district, int gardenID) {
        try{
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update Garden g set g.user.userID=:userID, g.ada=:ada,g.area=:area,g.parsel=:parsel,g.gardenName=:gardenName,g.city=:city,g.town=:town,g.district=:district" +
                " where g.gardenID=:gardenID and g.user.userID=:userID");

        query.executeUpdate();
        session.getTransaction().commit();


    } catch (Exception e) {
        System.out.println(e.getMessage());

    }
    }

    @Override
    public void updategarden(Garden garden) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(garden);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void deletegarden(int gardenID, int userID) {
        Session session = sessionFactory.openSession();
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Garden g where g.gardenID =: gardenID and g.user.userID=:userID");
        query.setParameter("gardenID", gardenID);
        query.setParameter("userID", userID);
        try {
            query.executeUpdate();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //ikinci methot;
    }//delete g,gp,c from Garden g left join Cost c on g.gardenID=c.gardenID left join GardenProduct gp on g.gardenID=gp.gardenID where g.gardenID=1 and g.userID=18;



}
