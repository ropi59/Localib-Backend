package fr.olivier.formationcda.ecf4.localib.users;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "email", nullable = false)
    private String email;
}
