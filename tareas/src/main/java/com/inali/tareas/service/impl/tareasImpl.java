package com.inali.tareas.service.impl;

import com.inali.tareas.model.dao.TareasDAO;
import com.inali.tareas.model.dto.TareasDto;
import com.inali.tareas.model.entity.Tareas;
import com.inali.tareas.service.ITareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class tareasImpl implements ITareas {
@Autowired
private TareasDAO tareasDAO;

    @Override
    public List<Tareas> lisTareas() {
        return (List) tareasDAO.findAll();
    }

    @Transactional
    @Override
    public Tareas save(TareasDto tareasdto) {
        Tareas tareas = Tareas.builder()
                .idTarea(tareasdto.getIdTarea())
                .descripcion(tareasdto.getDescripcion())
                .nombreActividad(tareasdto.getNombreActividad())
                .estatus(tareasdto.getEstatus())
                .build();

        return tareasDAO.save(tareas);
    }
    @Transactional(readOnly = true)
    @Override
    public Tareas findById(Integer id) {
        return tareasDAO.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Tareas tareas) {
        tareasDAO.delete(tareas);
    }

    @Override
    public boolean existsById(Integer id) {
        return tareasDAO.existsById(id);
    }

}
