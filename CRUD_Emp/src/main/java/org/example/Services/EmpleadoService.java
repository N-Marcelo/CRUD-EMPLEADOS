package org.example.Services;

import org.example.Model.Empleado;
import org.example.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> listarEmpleados(){
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarEmpleadoPorId(long id){
        return empleadoRepository.findById(id);
    }
    public Empleado guardarEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }
    public Empleado actualizarEmpleado(Long id, Empleado empleadoDetails){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        empleado.setNombre(empleadoDetails.getNombre());
        empleado.setApellido(empleadoDetails.getApellido());
        empleado.setCorreo(empleadoDetails.getCorreo());
        empleado.setPuesto(empleadoDetails.getPuesto());
        empleado.setEdad(empleadoDetails.getEdad());

        return empleadoRepository.save(empleado);
    }


    public String eliminarEmpleado(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return "Empleado eliminado correctamente";
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }
}
