package com.inali.tareas.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tareas")
public class Tareas implements Serializable {
    @Id
    @Column(name = "idTarea")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarea;
    @Column(name = "nombreActividad")
    private String nombreActividad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estatus")
    private String estatus;

}

