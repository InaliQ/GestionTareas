function cargarTareas() {
    fetch('http://localhost:8080/api/v1/clientes')
        .then(response => response.json())
        .then(data => {
            const tareasList = document.getElementById('tareasList');
            tareasList.innerHTML = ''; 

            const table = document.createElement('table');
            table.classList.add('tareas-table');

            // Encabezados de la tabla
            const headerRow = document.createElement('tr');
            const headers = ['Nombre de la actividad', 'Descripción', 'Estatus', 'Acciones'];
            headers.forEach(headerText => {
                const header = document.createElement('th');
                header.textContent = headerText;
                headerRow.appendChild(header);
            });
            table.appendChild(headerRow);

            data.object.forEach(tarea => {
                const row = document.createElement('tr');

                // Columnas de la fila
                const columns = [
                    tarea.nombreActividad,
                    tarea.descripcion,
                    tarea.estatus,
                    ''
                ];

                columns.forEach(columnText => {
                    const cell = document.createElement('td');
                    cell.textContent = columnText;
                    row.appendChild(cell);
                });

                // Botón de actualizar
                const actualizarBtn = document.createElement('button');
                actualizarBtn.textContent = 'Actualizar';
                actualizarBtn.addEventListener('click', () => {
                    actualizarTarea(tarea.idTarea);
                });

                const actionsCell = document.createElement('td');
                actionsCell.appendChild(actualizarBtn);
                row.appendChild(actionsCell);

                table.appendChild(row);
            });

            tareasList.appendChild(table);
        })
        .catch(error => {
            alert('Error al cargar las tareas.');
            console.error('Error:', error);
        });
}


        function actualizarTarea(idTarea) {
    const nuevoEstatus = prompt('Ingrese el nuevo estatus para la tarea:');
    const nuevaDescripcion = prompt('Ingrese la nueva descripción para la tarea:');
    
    if (nuevoEstatus !== null || nuevaDescripcion !== null) {
        fetch(`http://localhost:8080/api/v1/tarea/${idTarea}`)
            .then(response => response.json())
            .then(data => {
                const tareaActualizada = {
                    idTarea: idTarea,
                    nombreActividad: data.object.nombreActividad,
                    descripcion: nuevaDescripcion == '' ? data.object.descripcion:nuevaDescripcion,
                    estatus: nuevoEstatus == '' ? data.object.estatus:nuevoEstatus
                };
                fetch(`http://localhost:8080/api/v1/tarea/${idTarea}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(tareaActualizada)
                })
                .then(response => response.json())
                .then(data => {
                    alert('Tarea actualizada exitosamente!');
                    cargarTareas();
                })
                .catch(error => {
                    alert('Error al actualizar la tarea.');
                    console.error('Error:', error);
                });
            })
            .catch(error => {
                alert('Error al obtener los datos de la tarea.');
                console.error('Error:', error);
            });
    }
}


        window.addEventListener('load', cargarTareas);

        document.getElementById('tareaForm').addEventListener('submit', function(event) {
            event.preventDefault(); 

            const nombreActividad = document.getElementById('nombreActividad').value;
            const descripcion = document.getElementById('descripcion').value;
            const estatus = document.getElementById('estatus').value;

            const tarea = {
                nombreActividad: nombreActividad,
                descripcion: descripcion,
                estatus: estatus
            };

            fetch('http://localhost:8080/api/v1/tarea', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(tarea)
            })
            .then(response => response.json())
            .then(data => {
                alert('Tarea registrada exitosamente!');
                cargarTareas();
            })
            .catch(error => {
                alert('Error al registrar la tarea.');
                console.error('Error:', error);
            });
        });