package dao;

import model.Curso;
import util.DatabaseConnection;
import interfaces.iCursoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements iCursoDAO {

    @Override
    public void create(Curso curso) {
        String sql = "INSERT INTO cursos(nombreCurso) VALUES(?)";
        Connection con = DatabaseConnection.connect();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, curso.getNombreCurso());
            ps.executeUpdate();
            System.out.println("Curso insertado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting course: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }
    @Override
    public List<Curso> getAll() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id_curso, nombreCurso FROM cursos";
        Connection con = DatabaseConnection.connect();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cursos.add(new Curso(rs.getInt("id_curso"), rs.getString("nombreCurso")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing courses: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
        return cursos;
    }
    @Override
    public Curso getById(int id) {
        Curso curso = null;
        String sql = "SELECT id_curso, nombreCurso FROM cursos WHERE id_curso = ?";
        Connection con = DatabaseConnection.connect();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                curso = new Curso(rs.getInt("id_curso"), rs.getString("nombreCurso"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding course: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
        return curso;
    }
    @Override
    public void update(Curso curso) {
        String sql = "UPDATE cursos SET nombreCurso = ? WHERE id_curso = ?";
        Connection con = DatabaseConnection.connect();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, curso.getNombreCurso());
            ps.setInt(2, curso.getId());
            ps.executeUpdate();
            System.out.println("Curso actualizado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating course: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cursos WHERE id_curso = ?";
        Connection con = DatabaseConnection.connect();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Curso eliminado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting course: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }
}