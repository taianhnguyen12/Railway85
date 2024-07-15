package entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

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
}