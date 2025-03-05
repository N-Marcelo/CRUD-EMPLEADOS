package org.example.Controller;

import org.example.Model.Empleado;
import org.example.Services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> listarEmpleados(){
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable long id){
        return empleadoService.buscarEmpleadoPorId(id)
            .map(empleado -> ResponseEntity.ok(empleado))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado guardarEmpleado(@RequestBody Empleado empleado){
        return empleadoService.guardarEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetails){
        return empleadoService.actualizarEmpleado(id, empleadoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  eliminarEmpleado(@PathVariable long id){
        try {
            //Obtención de OK y un mensaje adicional
            String mensaje = empleadoService.eliminarEmpleado(id);
            return ResponseEntity.ok(mensaje);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
