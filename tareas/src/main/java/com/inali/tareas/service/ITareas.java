package com.inali.tareas.service;

import com.inali.tareas.model.dto.TareasDto;
import com.inali.tareas.model.entity.Tareas;

import java.util.List;

public interface ITareas {

    List<Tareas> lisTareas();
    Tareas save(TareasDto tareas);
    Tareas findById(Integer id);
    void delete(Tareas tareas);
    boolean existsById(Integer id);
}
