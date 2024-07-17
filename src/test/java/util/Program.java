package util;

import entity.Department;
import org.hibernate.internal.build.AllowSysOut;

public class Program {
    public static void main(String[] args) {
           try (var factory = HibernaterUtil.buildSessionFactory()) {
               factory.inTransaction(session -> {
                   var departmentA= new Department();
                   departmentA.setName("Giám Đốc");
                   departmentA.setType(Department.Type.DEV);
                   session.persist(departmentA);

               });
               factory.inTransaction(session -> {
                   var departmentA = new Department();
                   departmentA.setName("A Đốc");
departmentA.setType(Department.Type.KES);
                   session.persist(departmentA);

               });
               factory.inSession(session -> {
                   var hql = "From Department";
                  var departments =  session.createQuery(hql,Department.class)
                           .getResultList();
                   for (var department : departments) {
                       System.out.println("department = " + department);
                   }
               });

//               // tìm kiếm theo id(tìm)
//               factory.inSession(session ->  {
//                   var department = session.get(Department.class,1);
//                   System.out.println("department = " + department);
//               });
//
//               // tìm kiếm phòng ban theo tên
//               factory.inSession(session -> {
//var hql = "From  Department WHERE name = :name";
//var department = session
//        .createSelectionQuery(hql,Department.class)
//        .setParameter("name","A Đốc")
//        .uniqueResult();
//                   System.out.println("department = " + department);
//               });
//
//               // cap nhat phong ban ̣UPDATE
//               factory.inTransaction(session -> {
//                   var department = session.get(Department.class,1);
//
//                   department.setName("Kinh toanh");
//                   session.merge(department);
//               });
//
//               // DELETE
//               factory.inTransaction(session -> {
//                   var department = session.get(Department.class,2);
//                   session.remove(department);
//               });
           }
    }
}
