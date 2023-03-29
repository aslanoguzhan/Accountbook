package com.spring.dao;

import com.spring.model.Cost;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oguzhanaslan on 28.08.2020.
 */


@Repository
public class CostDAOImp implements CostDAO {


    @Setter
    @Autowired
    private SessionFactory sessionFactory;
    int pageSize = 20;

    @Override
    public void saveCost(Cost cost) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(cost);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @Override
    public void Delete(long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete FROM  Cost  where costID=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


    public List<Object> findAlltotalcost(int userID) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map(sum(c.fuel+c.seed+c.pesticide+c.chemicalfertilizers+c.organicfertilizers+c.irrigation+c.laborforce+c.machine)as toplam_masraf)from Cost c inner join c.user u where  u.userID=:userID ");
            //   "select sum(c.fuel+c.seed+c.pesticide+c.chemicalfertilizers+c.organicfertilizers+c.irrigation+c.laborforce+c.machine)from Cost c  where  c.userID=:userID ");

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
    public List<Object> getCategorycost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            //kategori bazlı masrafları listeleme
            //sum(c.fuel)as fuel,sum(c.seed)as seed,sum(c.pesticide) as pesticide,sum(c.chemicalfertilizers)as chemicalfertilizers,sum(c.organicfertilizers)as organicfertilizers ,sum(c.irrigation)as irrigation,sum(c.laborforce) as laborforce,sum(c.machine)as machine
            Query query = session.createQuery(
                    "select new Map (sum(c.fuel+c.seed+c.pesticide+c.chemicalfertilizers+c.organicfertilizers+c.irrigation+c.laborforce+c.machine)as toplam_masraf) from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }


    }

    /////Kategori bazlı masrafları getiren sorgular
    @Override
    public List<Object> getMachineCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map (sum(c.machine)as makina)from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }


    }

    @Override
    public List<Object> getFuelCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map (sum(c.fuel)as yakıt)from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Object> getSeedCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map (sum(c.seed)as tohum)from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Object> getChemicalfertilizersCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map (sum(c.chemicalfertilizers)as gübre)from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Object> getOrganicfertilizersCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map (sum(c.organicfertilizers)as organik_gübre)from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Object> getİrrigationCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map (sum(c.irrigation)as sulama)from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Object> getLaborforceCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map (sum(c.laborforce)as işgücü)from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public List<Object> getPresticideCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map (sum(c.pesticide)as ilaç)from Cost c inner join c.product p inner join c.garden g where p.productID=:productID and g.gardenID=:gardenID ");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

   @Override
    public List<Object> getMauntCost(int productID, int gardenID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select distinct new Map(substring(c.costDate,6,2) as ay, substring(c.costDate,1,4) as yıl, sum(c.seed)as tohum,sum(c.fuel)as yakıt," +
                    "sum(c.irrigation)as sulama, sum(c.machine)as machine,sum(c.organicfertilizers)as organik_gübre,sum(c.chemicalfertilizers)as gübre,sum(c.pesticide)as ilaç,sum(c.laborforce)as isgücü ) from Cost c" +
                               " where  c.product.productID=:productID and c.garden.gardenID=:gardenID"+
                               " group by  substring(c.costDate,6,2) , substring(c.costDate,1,4) " +
                               "order by   ay,yıl");
            query.setParameter("productID", productID);
            query.setParameter("gardenID", gardenID);

            List arrayList = query.getResultList();
            transaction.commit();

            return  arrayList;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }

    }


}
