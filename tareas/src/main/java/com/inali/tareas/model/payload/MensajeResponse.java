package com.inali.tareas.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
public class MensajeResponse implements Serializable {

    private String mensaje;
    private Object object;

}
