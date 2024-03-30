package com.inali.tareas.controller;

import com.inali.tareas.model.dto.TareasDto;
import com.inali.tareas.model.entity.Tareas;
import com.inali.tareas.model.payload.MensajeResponse;
import com.inali.tareas.service.ITareas;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Builder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TareasController {
    @Autowired
    private ITareas tareasService;

    @GetMapping("clientes")
    public ResponseEntity<?> showAll() {
        List<Tareas> getList = tareasService.lisTareas();
        if (getList == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);
    }


    @PostMapping("tarea")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody TareasDto tareasdto) {
        Tareas tareasSave = null;
        try {
            tareasSave = tareasService.save(tareasdto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(TareasDto.builder()
                            .idTarea(tareasSave.getIdTarea())
                            .nombreActividad(tareasSave.getNombreActividad())
                            .descripcion(tareasSave.getDescripcion())
                            .estatus(tareasSave.getEstatus())
                            .build())
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("tarea/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody TareasDto tareasdto, @PathVariable Integer id) {

        Tareas tareasUpdate = null;
        try {
            if (tareasService.existsById(id)) {
                tareasdto.setIdTarea(id);
                tareasUpdate = tareasService.save(tareasdto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado Correctamente")
                        .object(TareasDto.builder()
                                .idTarea(tareasUpdate.getIdTarea())
                                .nombreActividad(tareasUpdate.getNombreActividad())
                                .descripcion(tareasUpdate.getDescripcion())
                                .estatus(tareasUpdate.getEstatus())
                                .build())
                        .build(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("tarea/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Tareas tareasDelete = tareasService.findById(id);
            tareasService.delete(tareasDelete);
            return new ResponseEntity<>(tareasDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("tarea/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Tareas tareas = tareasService.findById(id);
        if (tareas == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Prueba de ejecucion")
                        .object(TareasDto.builder()
                                .idTarea(tareas.getIdTarea())
                                .nombreActividad(tareas.getNombreActividad())
                                .descripcion(tareas.getDescripcion())
                                .estatus(tareas.getEstatus())
                                .build())
                        .build(),HttpStatus.OK);
    }
}