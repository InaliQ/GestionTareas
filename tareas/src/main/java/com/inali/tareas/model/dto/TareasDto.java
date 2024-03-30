package com.inali.tareas.model.dto;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class TareasDto implements Serializable {
    private Integer idTarea;
    private String nombreActividad;
    private String descripcion;
    private String estatus;

}

