package util;

import entity.Department;
import entity.GroupAccount;

public class Program {
    public static void main(String[] args) {
           try (var factory = HibernaterUtil.buildSessionFactory()) {
               factory.inTransaction(session -> {
                 var groupAccount = new GroupAccount();
               groupAccount.setGroupId(1);
               groupAccount.setAccountId(4);
                 session.persist(groupAccount);

               });
               factory.inTransaction(session -> {
                   var groupAccount = new GroupAccount();
                   var pk = new GroupAccount.PrimaryKey();
                   groupAccount.setGroupId(7);
                   groupAccount.setAccountId(9);
                   session.persist(groupAccount);

               });
               factory.inSession(session -> {
                   var hql = "From GroupAccount ";
                      var groupAccounts =  session.createQuery(hql,GroupAccount.class)
                           .getResultList();
                   for (var groupAccount : groupAccounts) {
                       System.out.println("GroupAccount " + groupAccount);
                   }
               });


           }
    }
}
