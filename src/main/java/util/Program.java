package util;

import entity.Account;
import entity.Department;
import entity.Group;
import entity.GroupAccount;
import org.hibernate.internal.build.AllowSysOut;

public class Program {
    public static void main(String[] args) {
           try (var factory = HibernaterUtil.buildSessionFactory()) {
               factory.inTransaction(session -> {
                var group = new Group();
                     group.setName("Hibernate Group");
                     session.persist(group);
                     var account = new Account();
                     account.setName("haihuy");
                     account.setEmail("haihi1010@gmail.com");
                     account.setGroup(group);
                     session.persist(account);
               });

               factory.inSession(session -> {
                   var hql = "From Account ";
                      var AccountS =  session.createQuery(hql,Account.class)
                           .getResultList();
                   for (var account : AccountS) {
                       System.out.println("Account " + account.getName());
                       System.out.println("group " + account.getGroup().getName());
                   }
               });
               factory.inSession(session -> {
                   var hql = "From Group ";
                   var Groups =  session.createQuery(hql,Group.class)
                           .getResultList();
                   for (var group : Groups) {
                       System.out.println("Group " + group.getName());
                       System.out.println("Accout " + group.getAccount().getName());
                   }
               });


           }
    }
}
