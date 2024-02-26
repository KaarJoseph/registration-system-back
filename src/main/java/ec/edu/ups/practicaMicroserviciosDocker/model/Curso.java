package ec.edu.ups.practicaMicroserviciosDocker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int numeroDeHoras;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDeHoras() {
        return numeroDeHoras;
    }

    public void setNumeroDeHoras(int numeroDeHoras) {
        this.numeroDeHoras = numeroDeHoras;
    }

}
