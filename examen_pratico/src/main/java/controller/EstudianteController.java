package controller;

import model.Estudiante;
import service.EstudianteService;

import java.util.List;

public class EstudianteController {
    private EstudianteService estudianteService;

    public EstudianteController() {
        this.estudianteService = new EstudianteService();
    }

    public boolean insertarEstudiante(String nombre, String apellido, String correo) {
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            return false;
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setCorreo(correo);
        estudiante.setEstado(true);

        return estudianteService.insertar(estudiante);
    }

    public boolean modificarEstudiante(int id, String nombre, String apellido, String correo) {
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            return false;
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setId(id);
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setCorreo(correo);
        estudiante.setEstado(true);

        return estudianteService.modificar(estudiante);
    }

    public boolean eliminarEstudiante(int id) {
        return estudianteService.eliminarLogico(id);
    }

    public List<Estudiante> listarEstudiantesActivos() {
        return estudianteService.listarActivos();
    }

    public List<Estudiante> listarEstudiantesEliminados() {
        return estudianteService.listarEliminados();
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        return estudianteService.obtenerPorId(id);
    }
}
