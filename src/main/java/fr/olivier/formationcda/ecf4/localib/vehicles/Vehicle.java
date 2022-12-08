package fr.olivier.formationcda.ecf4.localib.vehicles;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name= "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "immat", nullable = false)
    private String immat;
    @Column(name = "state", nullable = false)
    private char state;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "disponibility", nullable = false)
    private Disponibility disponibility;
    @Column(name = "vehiclePic")
    private String vehiclePic;


}
