package service;

import database.ConexionDB;
import model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteService {

    // Método para insertar un nuevo estudiante
    public boolean insertar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getCorreo());
            stmt.setBoolean(4, estudiante.isEstado());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar estudiante: " + e.getMessage());
            return false;
        }
    }

    // Método para modificar un estudiante existente
    public boolean modificar(Estudiante estudiante) {
        String sql = "UPDATE estudiantes SET nombre = ?, apellido = ?, correo = ?, estado = ? WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getCorreo());
            stmt.setBoolean(4, estudiante.isEstado());
            stmt.setInt(5, estudiante.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar estudiante: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar lógicamente un estudiante (cambiar estado a 0)
    public boolean eliminarLogico(int id) {
        String sql = "UPDATE estudiantes SET estado = 0 WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar lógicamente estudiante: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todos los estudiantes activos (estado = 1)
    public List<Estudiante> listarActivos() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE estado = 1";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setCorreo(rs.getString("correo"));
                estudiante.setEstado(rs.getBoolean("estado"));

                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar estudiantes activos: " + e.getMessage());
        }

        return estudiantes;
    }

    // Método para listar todos los estudiantes eliminados (estado = 0)
    public List<Estudiante> listarEliminados() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE estado = 0";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setCorreo(rs.getString("correo"));
                estudiante.setEstado(rs.getBoolean("estado"));

                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar estudiantes eliminados: " + e.getMessage());
        }

        return estudiantes;
    }

    // Método para obtener un estudiante por su ID
    public Estudiante obtenerPorId(int id) {
        String sql = "SELECT * FROM estudiantes WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setId(rs.getInt("id"));
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setApellido(rs.getString("apellido"));
                    estudiante.setCorreo(rs.getString("correo"));
                    estudiante.setEstado(rs.getBoolean("estado"));

                    return estudiante;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener estudiante por ID: " + e.getMessage());
        }

        return null;
    }
}
