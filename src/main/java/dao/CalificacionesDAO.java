package dao;

import model.Calificaciones;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionesDAO {

    public void createScore(Calificaciones c) {
        String sql = "INSERT INTO calificaciones(alumno_id, asignatura_id, nota, trimestre, tipo) VALUES(?,?,?,?,?)";
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getAlumnoId());
            ps.setInt(2, c.getAsignaturaId());
            ps.setDouble(3, c.getNota());
            ps.setString(4, c.getTrimestre());
            ps.setString(5, c.getTipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.err.println("Error: Ya existe esa nota para el alumno en ese trimestre/tipo.");
            }
            throw new RuntimeException("Error al crear calificación", e);
        }
    }

    public List<Calificaciones> getAll() {
        List<Calificaciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM calificaciones";
        try (Connection con = DatabaseConnection.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapResultSetToCalificacion(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Calificaciones> getBySubject(int asignaturaId) {
        List<Calificaciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM calificaciones WHERE asignatura_id = ?";
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, asignaturaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapResultSetToCalificacion(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Calificaciones> getByStudent(int alumnoId) {
        List<Calificaciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM calificaciones WHERE alumno_id = ?";
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alumnoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapResultSetToCalificacion(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<String> getAllStudentNames() {
        List<String> nombres = new ArrayList<>();

        String sql = "SELECT nombre_alumno FROM alumnos ORDER BY alumno_id ASC";
        try (Connection con = DatabaseConnection.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                nombres.add(rs.getString("nombre_alumno"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener nombres de alumnos: " + e.getMessage(), e);
        }
        return nombres;
    }

    public void updateScore(Calificaciones c) {
        String sql = "UPDATE calificaciones SET nota = ? " +
                "WHERE alumno_id = ? AND asignatura_id = ? AND trimestre = ? AND tipo = ?";
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, c.getNota());
            ps.setInt(2, c.getAlumnoId());
            ps.setInt(3, c.getAsignaturaId());
            ps.setString(4, c.getTrimestre());
            ps.setString(5, c.getTipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public double getFinalGrade(int alumnoId, int asignaturaId, String trimestre) {
        String sql = "SELECT c.nota, c.tipo, a.valor_practico, a.valor_examen " +
                "FROM calificaciones c JOIN asignaturas a ON c.asignatura_id = a.id_asignatura " +
                "WHERE c.alumno_id = ? AND c.asignatura_id = ? AND c.trimestre = ?";
        double notaFinal = 0;
        try (Connection con = DatabaseConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alumnoId);
            ps.setInt(2, asignaturaId);
            ps.setString(3, trimestre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double nota = rs.getDouble("nota");
                    String tipo = rs.getString("tipo");
                    double peso = tipo.equalsIgnoreCase("Práctico") ?
                            rs.getDouble("valor_practico") : rs.getDouble("valor_examen");
                    notaFinal += (nota * peso);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notaFinal;
    }


    public double getGlobalFinalGrade(int alumnoId, int asignaturaId) {
        double sumaTrimestres = 0;
        sumaTrimestres += getFinalGrade(alumnoId, asignaturaId, "1");
        sumaTrimestres += getFinalGrade(alumnoId, asignaturaId, "2");
        sumaTrimestres += getFinalGrade(alumnoId, asignaturaId, "3");
        return sumaTrimestres / 3.0;
    }
    public List<Object[]> getAlumnos() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT alumno_id, nombre_alumno FROM alumnos ORDER BY nombre_alumno";
        try (Connection con = DatabaseConnection.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{rs.getInt(1), rs.getString(2)});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Object[]> getAsignaturas() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT id_asignatura, nombre_asignatura FROM asignaturas ORDER BY nombre_asignatura";
        try (Connection con = DatabaseConnection.connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{rs.getInt(1), rs.getString(2)});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    // Métod auxiliar para mapear el ResultSet
    private Calificaciones mapResultSetToCalificacion(ResultSet rs) throws SQLException {
        Calificaciones c = new Calificaciones();
        c.setId(rs.getInt("id_calificacion"));
        c.setAlumnoId(rs.getInt("alumno_id"));
        c.setAsignaturaId(rs.getInt("asignatura_id"));
        c.setNota(rs.getDouble("nota"));
        c.setTrimestre(rs.getString("trimestre"));
        c.setTipo(rs.getString("tipo"));
        return c;
    }
}