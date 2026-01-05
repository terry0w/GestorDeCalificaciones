package dao;

import model.Alumno;
import util.DatabaseConnection;
import interfaces.iAlumnoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO implements iAlumnoDAO {

    @Override
    public void create(Alumno alumno) {
        String sql = "INSERT INTO alumnos(nif, nombre_alumno, email, curso_id) VALUES(?,?,?,?)";
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, alumno.getNif());
            ps.setString(2, alumno.getNombreAlumno());
            ps.setString(3, alumno.getEmail());
            ps.setInt(4, alumno.getCursoId());
            ps.executeUpdate();
            System.out.println("Alumno insertado correctamente.");

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar alumno: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Alumno> getAll() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT id_alumno, nif, nombre_alumno, email, curso_id FROM alumnos";

        try (Connection con = DatabaseConnection.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                alumnos.add(new Alumno(
                        rs.getInt("id_alumno"),
                        rs.getString("nif"),
                        rs.getString("nombre_alumno"),
                        rs.getString("email"),
                        rs.getInt("curso_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar alumnos: " + e.getMessage(), e);
        }
        return alumnos;
    }

    @Override
    public List<Alumno> getByNif(String nif) {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT id_alumno, nif, nombre_alumno, email, curso_id FROM alumnos WHERE nif = ?";

        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nif);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    alumnos.add(new Alumno(
                            rs.getInt("id_alumno"),
                            rs.getString("nif"),
                            rs.getString("nombre_alumno"),
                            rs.getString("email"),
                            rs.getInt("curso_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar por NIF: " + e.getMessage(), e);
        }
        return alumnos;
    }

    @Override
    public void update(Alumno alumno) {
        String sql = "UPDATE alumnos SET nif = ?, nombre_alumno = ?, email = ?, curso_id = ? WHERE id_alumno = ?";
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, alumno.getNif());
            ps.setString(2, alumno.getNombreAlumno());
            ps.setString(3, alumno.getEmail());
            ps.setInt(4, alumno.getCursoId());
            ps.setInt(5, alumno.getId());

            ps.executeUpdate();
            System.out.println("Alumno actualizado correctamente.");

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar alumno: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM alumnos WHERE id_alumno = ?";
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Alumno eliminado correctamente.");

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar alumno: " + e.getMessage(), e);
        }
    }
}