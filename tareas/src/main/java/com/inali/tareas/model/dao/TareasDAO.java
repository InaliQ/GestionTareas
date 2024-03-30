package com.inali.tareas.model.dao;

import com.inali.tareas.model.entity.Tareas;
import org.springframework.data.repository.CrudRepository;

public interface TareasDAO extends CrudRepository<Tareas,Integer> {

}
