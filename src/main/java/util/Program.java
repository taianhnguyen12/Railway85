package util;

import entity.Department;

public class Program {
    public static void main(String[] args) {
           try (var factory = HibernaterUtil.buildSessionFactory()) {
               factory.inTransaction(session -> {
                   var department= new Department();
                   department.setName("Giám Đốc");
                   department.setType(Department.Type.DEV);
                   session.persist(department);

               });
               factory.inTransaction(session -> {
                   var department = new Department();
                   department.setName("A Đốc");
                   department.setType(Department.Type.KES);
                   session.persist(department);

               });
               factory.inSession(session -> {
                   var hql = "From Department";
                  var departments =  session.createQuery(hql,Department.class)
                           .getResultList();
                   for (var department : departments) {
                       System.out.println("department = " + department);
                   }
               });


           }
    }
}
