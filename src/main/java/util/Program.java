package util;

import entity.Account;
import entity.Department;
import entity.Group;
import entity.GroupAccount;
import org.hibernate.internal.build.AllowSysOut;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
           try (var factory = HibernaterUtil.buildSessionFactory()) {
               factory.inTransaction(session -> {


                var group1 = new Group();
                     group1.setName("Hibernate Group");
                     session.persist(group1);

                   var group2 = new Group();
                   group2.setName("City Group");
                   session.persist(group2);

                     var account = new Account();
                     account.setName("haihuy");
                     account.setEmail("haihi1010@gmail.com");

                     session.persist(account);


                   var account2 = new Account();
                   account2.setName("Thao");
                   account2.setEmail("Thao3co@gmail.com");

                   session.persist(account2);


                   account.setGroups(Arrays.asList(group1,group2));
                   account2.setGroups(Arrays.asList(group1,group2));

                   group1.setAccounts(Arrays.asList(account,account2));
                   group2.setAccounts(Arrays.asList(account,account2));

                   session.persist(group1);
                   session.persist(group2);
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
