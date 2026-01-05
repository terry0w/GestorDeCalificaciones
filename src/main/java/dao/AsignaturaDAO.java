package dao;

import model.Asignatura;
import util.DatabaseConnection;
import interfaces.iAsignaturaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO implements iAsignaturaDAO{

    @Override
    public void create(Asignatura asignatura) {
        String sql = "INSERT INTO asignaturas(nombre_asignatura, curso_id, valor_practico, valor_examen) VALUES(?,?,?,?)";
        Connection con = DatabaseConnection.connect();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, asignatura.getNombreAsignatura());
            ps.setInt(2, asignatura.getCursoId());
            ps.setDouble(3, asignatura.getValorPractico());
            ps.setDouble(4, asignatura.getValorExamen());
            ps.executeUpdate();
            System.out.println("Asignatura insertada correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting subject: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }
    @Override
    public List<Asignatura> getAll() {
        List<Asignatura> asignaturas = new ArrayList<>();
        String sql = "SELECT id_asignatura, nombre_asignatura, curso_id, valor_practico, valor_examen FROM asignaturas";
        Connection con = DatabaseConnection.connect();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Asignatura a = new Asignatura();
                a.setId(rs.getInt("id_asignatura"));
                a.setNombreAsignatura(rs.getString("nombre_asignatura"));
                a.setCursoId(rs.getInt("curso_id"));
                a.setValorPractico(rs.getDouble("valor_practico"));
                a.setValorExamen(rs.getDouble("valor_examen"));
                asignaturas.add(a);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listing subjects: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
        return asignaturas;
    }
    @Override
    public Asignatura getById(int id) {
        Asignatura asignatura = null;
        String sql = "SELECT id_asignatura, nombre_asignatura, curso_id, valor_practico, valor_examen FROM asignaturas WHERE id_asignatura = ?";
        Connection con = DatabaseConnection.connect();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                asignatura = new Asignatura();
                asignatura.setId(rs.getInt("id_asignatura"));
                asignatura.setNombreAsignatura(rs.getString("nombre_asignatura"));
                asignatura.setCursoId(rs.getInt("curso_id"));
                asignatura.setValorPractico(rs.getDouble("valor_practico"));
                asignatura.setValorExamen(rs.getDouble("valor_examen"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error finding subject: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
        return asignatura;
    }
    @Override
    public void update(Asignatura asignatura) {
        String sql = "UPDATE asignaturas SET nombre_asignatura = ?, curso_id = ?, valor_practico = ?, valor_examen = ? WHERE id_asignatura = ?";
        Connection con = DatabaseConnection.connect();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, asignatura.getNombreAsignatura());
            ps.setInt(2, asignatura.getCursoId());
            ps.setDouble(3, asignatura.getValorPractico());
            ps.setDouble(4, asignatura.getValorExamen());
            ps.setInt(5, asignatura.getId());
            ps.executeUpdate();
            System.out.println("Asignatura actualizada correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating subject: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM asignaturas WHERE id_asignatura = ?";
        Connection con = DatabaseConnection.connect();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Asignatura eliminada correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting subject: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeConnection(con);
        }
    }
}
