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


                   var account2 = new Account();
                   account2.setName("Thao");
                   account2.setEmail("Thao3co@gmail.com");
                   account2.setGroup(group);
                   session.persist(account2);
               });

               factory.inSession(session -> {
                   var hql = "From Group ";
                      var Groups =  session.createQuery(hql,Group.class)
                           .getResultList();

                   for (var group : Groups) {
                       System.out.println("Group " + group.getName());

                    var accounts = group.getAccounts();

                    for(var account : accounts) {
                        System.out.println("account = " + account.getName());
                    }
                   }
               });



           }
    }
}
