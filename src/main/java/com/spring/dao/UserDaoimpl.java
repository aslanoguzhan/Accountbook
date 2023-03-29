package com.spring.dao;

import com.spring.model.AppUser;
import com.spring.model.Cost;
import com.spring.model.CustomUser;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oguzhanaslan on 7.09.2020.
 */

@Repository
public class UserDAOImpl implements UserDAO {


    @Setter
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void insertUser(AppUser user) {

        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public List<Object> listAllUsers() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select new Map(u.userID as userID ,u.userName as userName," +
                    "u.userEmail as userEmail,u.status as status) from User u");
            List<Object> userList = query.list();
            transaction.commit();
            return userList;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Boolean isUserExist(String email) {
        Session session = sessionFactory.openSession();

        try {
            Query query = session.createQuery("select a.userID from AppUser as a where a.userEmail=:email");
            query.setParameter("email", email);
            if (query.getResultList().size() > 0)
                return true;
            else return false;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<AppUser>  checkStandardCredentials(String userEmail, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("select new Map( a.userID as userID,a.userName as userName,a.userEmail as userEmail) from AppUser as  a " +
                    "where a.userEmail=:userEmail and a.userPassword =: userPassword ");
            query.setParameter("userEmail", userEmail);
            query.setParameter("userPassword", password);

            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }

    }

    @Override
    public Boolean checkUserCode(String email, String code) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("select a.resetCode from AppUser a where a.userEmail=:email and a.resetCode=:code ");
        query.setParameter("email", email);
        query.setParameter("code", code);
        transaction.commit();
        if (query.uniqueResult() != null) {
            return true;
        } else
            return false;
    }

    @Override
    public AppUser updateUserStatus(String email) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from AppUser where userEmail =: userEmail");
            query.setParameter("userEmail", email);


            AppUser tempUser = (AppUser) query.uniqueResult();

            AppUser upUser = (AppUser) session.get(AppUser.class, tempUser.getUserID());
            session.update(upUser);
            tx.commit();
            session.close();
            upUser.setUserPassword(null);
            return upUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean isUserActive(String email) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = sessionFactory.getCurrentSession().createQuery("select a.userEmail from AppUser as a  where userEmail =: email");
        query.setParameter("userEmail", email);
        session.close();
        tx.commit();
        if (query.uniqueResult() != null)
            return true;
        else
            return false;
    }

    @Override
    public String changeusername(String email, String userName) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("from AppUser   where userEmail =:email");
            query.setParameter("email", email);


            AppUser tempUser = (AppUser) query.uniqueResult();

            AppUser upUser = (AppUser) session.get(AppUser.class, tempUser.getUserID());
            upUser.setUserName(userName);


            session.update(upUser);
            transaction.commit();
            session.close();
            return "ok";

        } catch (Exception e) {

            System.out.println("HATAAA:" + e.getMessage());
            return null;
        }
    }

    @Override
    public Long getCostcount(String email, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select COUNT(*)  from Cost where user.userEmail =: email  ");
        query.setParameter("email", email);


        return (Long) query.getSingleResult();
    }

    @Override
    public String changepassword(String email, String password, String newpw) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("select a.userID from AppUser as a where a.userEmail =: userEmail and a.userPassword=: password  ");
            query.setParameter("userEmail", email);
            query.setParameter("password", password);
            query.setParameter("newpw", newpw);


            if (query.uniqueResult() != null) {
                AppUser tempUser = (AppUser) query.uniqueResult();

                AppUser upUser = (AppUser) session.get(AppUser.class, tempUser.getUserID());
                upUser.setUserPassword(newpw);


                session.update(upUser);
                tx.commit();
                session.close();
                return "ok";
            } else {
                System.out.println("NO UNİQUE RESULT");
                return "User does not Exist!!!";

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean changeUserCode(String email, long code) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from AppUser  where userEmail =: userEmail and code=:code ");
        query.setParameter("userEmail", email);
        query.setParameter("code", code);
        if (query.uniqueResult() != null) {
            changeUserCode(email, code);
            return true;
        } else
            return false;
    }

    @Override
    public CustomUser findUserByEmail(String userEmail) {
        try {
            Session session = sessionFactory.openSession();


            CustomUser cUser = new CustomUser();
            Query query = sessionFactory.getCurrentSession()
                    .createQuery("from AppUser where userEmail =: userEmail");
            query.setParameter("userEmail", userEmail);

            AppUser aUser = (AppUser) query.uniqueResult();
            session.close();
            cUser.setUserID(aUser.getUserID());
            cUser.setUserEmail(aUser.getUserEmail());
            cUser.setUserName(aUser.getUserName());


            return cUser;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Email");
            return null;
        }


    }

    @Override
    public Boolean isUser(AppUser user) {
        return null;
    }

    @Override
    public List<Cost> getCost(String email) {
        Session session = sessionFactory.openSession();
        CustomUser cUser = new CustomUser();
        cUser = findUserByEmail(email);
        Query query = session.createQuery("select new map(c.costID as costID,c.cost as cost )from Cost c where user.userEmail =:email  ");

        query.setParameter("email", email);
        List<Cost> costList = query.list();

        return costList;
    }

    @Override
    public List<Object> getUsercosts(String email) {

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select a.userID as userID ,a.userName as userName,a.userSurname as userSurname," +
                "a.userEmail as userEmail,a.userToken as userToken," +
                "a.status as status from AppUser a where userEmail =: email");

        query.setParameter("email", email);
        if (query.uniqueResult() != null) {
            CustomUser cUser = findUserByEmail(email);
            Query query3 = session.createQuery("select new Map(c.cost as cost,c.costID as costID," +
                    "c.costDate as date) from Cost  c where user.userID =: id ORDER BY  costDate ASC ");
            query3.setParameter("id", cUser.getUserID());


            System.out.println(cUser.getUserID());


            List<Object> costlist = query3.list();
            transaction.commit();


            return costlist;
        } else {
            return null;

        }

    }

    @Override
    public List<Object> getCategorizecosts(String userEmail, String category) {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select a.userID as userID ,a.userName as userName," +
                "a.userEmail as userEmail,a.userToken as userToken," +
                "a.userType as userType,a.status as status from User a where userEmail =: email");
        CustomUser cUser = findUserByEmail(userEmail);
        System.out.println(cUser.getUserID());


        if (category.equals("Makina") || category.equals("Tohum") || category.equals("Yakıt") || category.equals("Organik Gübre")
                || category.equals("Kimyasal Gübre") || category.equals("İlaç") || category.equals("İş Gücü") || category.equals("Sulama")) {

            Query general = session.createQuery("select new Map(c.cost as cost,c.costID as costID,c.costDate as date) from Cost  c where user.userID =: id  ORDER BY  costDate ASC");
            general.setParameter("id", cUser.getUserID());
            List<Object> costlist = general.list();
            transaction.commit();
            return costlist;


        } else {
            Query cost = session.createQuery("select new Map(c.cost as cost,c.costID as costID,c.costDate as date) from Cost  c where user.userID =: id and c.cost.category ='Sulama' " +
                    "or c.cost.category ='Makina' or c.cost.category ='Tohum' or  c.cost.category ='Yakıt' or c.cost.category ='Organik Gübre'or c.cost.category ='Kimyasal Gübre'or c.cost.category ='İlaç'or c.cost.category ='İş Gücü' ORDER BY  costDate ASC");
            cost.setParameter("id", cUser.getUserID());
            List<Object> costlist = cost.list();
            transaction.commit();
            return costlist;
        }


    }

    @Override
    public List<Object> getcategoryinfo(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query3 = session.createQuery("select new Map(count(c.category) as count,c.category as category) from Cost c,Garden g , User  u where c.user.userID = u.userID and " +
                "g.garden.gardenID = c.costID and u.userEmail =:email group by c.category");
        query3.setParameter("email", email);


        List<Object> costList = query3.list();
        transaction.commit();
        return costList;
    }

    @Override
    public Boolean insertpwcode(String email, String code) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createSQLQuery("update AppUser  a set a.resetCode=:code where a.userEmail=:email");
            query.setParameter("email", email);
            query.setParameter("code", code);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean setpassword(String email, String newpw, String token) {

        try {
            if (token == null)
                return false;
            else {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                Query query = session.createSQLQuery("update AppUser  a set a.userPassword=:newpw where a.userEmail =:email and a.resetCode =:token");
                query.setParameter("email", email);
                query.setParameter("newpw", newpw);
                query.setParameter("token", token);
                query.executeUpdate();
                session.getTransaction().commit();
                return true;
            }

        } catch (Exception e) {
            System.out.println("HATA " + e.getMessage());
            return false;
        }

    }

    @Override
    public List<Object> findAllgarden(int userID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select distinct new Map( g.gardenID as gardenID,u.userID as user, g.gardenName as gardenName,gp.product.productName as productName ) from Garden g inner join  g.user u inner join g.gardenProduct gp where u.userID =:userID    ");


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
    public List<Object> finaAllproduct(int userID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map( p.productID as productID,u.userID as user,p.productName as productName,sum(gp.totalproduction) as production,gp.garden.gardenName as gardenName ) from Product p" +
                            " inner join p.gardenProduct gp inner join gp.user u where u.userID =:userID group by p.productID ,u.userID ,p.productName , gp.garden.gardenName ");


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
    public List<Object> findAllcost(int userID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map(sum(c.fuel+c.seed+c.pesticide+c.chemicalfertilizers+c.organicfertilizers+c.irrigation+c.laborforce+c.machine)as totalcost,g.gardenName as gardenName,p.productName as productName,u.userID as user) from Cost c " +
                            "inner join c.garden g  inner join c.product p inner join c.user u where u.userID =:userID group by  g.gardenName,p.productName,u.userID");


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
    public Boolean newpassword(String email, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = sessionFactory
                .getCurrentSession()
                .createSQLQuery("update  AppUser set userPassword=:password where userEmail=:email  ");
        query.setParameter("email", email);
        query.setParameter("password", password);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Object> userinfo(int userID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "select new Map( u.userID as userID, u.userName as userName,u.userEmail as userEmail,u.userPassword as userPassword) from AppUser u  where u.userID =:userID  ");


            query.setParameter("userID", userID);
            List costlist = query.getResultList();
            transaction.commit();
            return costlist;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }
}
