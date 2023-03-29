package com.spring.dao;

import com.spring.model.Product;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oguzhanaslan on 1.09.2020.
 */

@Repository
public class ProductDAOImp implements ProductDAO {


    @Setter
    @Autowired
    private SessionFactory sessionFactory;
    int pageSize = 20;


    @Override
    public void saveProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }


    @Override
    public void Delete(long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete FROM  Product  where productID=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Object> findAlltotalproduct(int gardenID) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map( sum(gp.totalproduction) as totalProduction) from GardenProduct gp inner join gp.garden g where g.gardenID=:gardenID");
            query.setParameter("gardenID", gardenID);
            List productlist = query.getResultList();
            transaction.commit();
            return productlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public List<Object> findAllcategorytotalproduct(int productID, int gardenID) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map( sum(gp.totalproduction ) as totalProduction) from GardenProduct gp inner join gp.garden g inner join gp.product p where g.gardenID=:gardenID and p.productID=:productID");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List productlist = query.getResultList();
            transaction.commit();
            return productlist;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public double getPercost(int productID, int gardenID) {


        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("select sum(c.seed+c.fuel+c.machine+c.pesticide+c.irrigation+" +
                    "c.laborforce+c.chemicalfertilizers+c.organicfertilizers)as costtotal from Cost c " +
                    "where c.garden.gardenID=:gardenID and c.product.productID=:productID");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            Query query1 = session.createQuery("select sum(gr.totalproduction) from GardenProduct gr " +
                    "where gr.garden.gardenID =:gardenID1 and gr.product.productID =:productID1");

            query1.setParameter("productID1", productID);
            query1.setParameter("gardenID1", gardenID);


            String s = query.getSingleResult().toString();
            String s1 = query1.getSingleResult().toString();
            double x = Double.parseDouble(s);
            double x1 = Double.parseDouble(s1);
            double result = (x / x1);

            transaction.commit();
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Double.parseDouble(null);
        }
    }

    @Override
    public List<Product> findByProductId(int productID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map(g.gardenID as gardenID, g.gardenName as gardenName) from Garden g inner join g.product p  where p.productID=:productID  ");

            query.setParameter("productID", productID);
            List<Product> products = query.getResultList();
            transaction.commit();
            return products;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Product> listAllproduct() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select new Map(p.productName as productName,p.productID as productID) from Product p");
            List<Product> produclist = query.list();
            transaction.commit();
            return produclist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }


}