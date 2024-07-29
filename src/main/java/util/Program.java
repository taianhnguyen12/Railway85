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
                     group1.setName("Hibernate city");
                     session.persist(group1);

                   var group2 = new Group();
                   group2.setName("Hrm Group");
                   session.persist(group2);

                     var account = new Account();
                     account.setName("haihuy");
                     account.setEmail("htoo@gmail.com");

                     session.persist(account);


                   var account2 = new Account();
                   account2.setName("Thao");
                   account2.setEmail("mato@gmail.com");
                   session.persist(account2);

var groupAccount1 = new GroupAccount();
groupAccount1.setAccount(account);
groupAccount1.setGroup(group1);
session.persist(groupAccount1);

var groupAccount2 = new GroupAccount();
groupAccount2.setAccount(account2);
groupAccount2.setGroup(group1);
session.persist(groupAccount2);

var groupAccount3 = new GroupAccount();
groupAccount3.setAccount(account);
groupAccount3.setGroup(group2);
session.persist(groupAccount3);

var groupAccount4 = new GroupAccount();
groupAccount4.setAccount(account2);
groupAccount4.setGroup(group2);
session.persist(groupAccount4);


               });

               factory.inSession(session -> {
                   var hql = "From Group ";
                      var groups =  session.createQuery(hql,Group.class)
                           .getResultList();

                   for (var group : groups) {
                       System.out.println("Group " + group.getName());

                    var groupAccounts = group.getGroupAccounts();

                    for(var groupAccount : groupAccounts) {
                        System.out.println("account = " + groupAccount.getAccount().getName());
                        System.out.println("jointed ad = " + groupAccount.getJoinedAt());
                    }
                   }
               });



           }
    }
}
