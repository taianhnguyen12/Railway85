package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "accout")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",length = 50,nullable=false)
    private String name;

    @Column(name = "email",length = 100,unique=true,nullable=false)
    private String email;


    @ManyToOne
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id",

            nullable=false
    )
    private Group group;

}
