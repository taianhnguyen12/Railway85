package entity;


import converter.DepartmentTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;
import org.hibernate.type.descriptor.jdbc.UUIDJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "id")
   @GenericGenerator(
           name = "department_id_generator",
           strategy = "generator.DepartmentIdGenerator"
   )
    @GeneratedValue(generator =  "department_id_generator")
//    @SequenceGenerator(
//            name = "department_id_generator",
//            sequenceName = "department_id_sequence",
//            initialValue = 5,
//            allocationSize = 1
//    )
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//     generator ="department_id_generator")
    private String id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "type",nullable = false)
    @Convert( converter = DepartmentTypeConverter.class)
    private Type type;


    @Column(name = "create_at",nullable = false, updatable = false)
    @CreationTimestamp // tự động cập nhật
    private LocalDateTime createAt;


    @Column(name = "update_at",nullable = false)
    @UpdateTimestamp
     private LocalDateTime update;

    @PrePersist
    public void prepersist() {
        System.out.println("trươc khi thêm vào database");
    }

    public enum Type {
        DEV,TES,KES,MASTER,PROJECT
    }
}