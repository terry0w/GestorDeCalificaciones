package controller;

import dao.CalificacionesDAO;
import model.Calificaciones;

import java.util.List;

public class CalificacionesController {

    private CalificacionesDAO dao;

    public CalificacionesController() {
        dao = new CalificacionesDAO();
    }

    // 🔹 CRUD
    public void create(int alumnoId, int asignaturaId, double nota, String trimestre, String tipo) {
        Calificaciones c = new Calificaciones();
        c.setAlumnoId(alumnoId);
        c.setAsignaturaId(asignaturaId);
        c.setNota(nota);
        c.setTrimestre(trimestre);
        c.setTipo(tipo);
        dao.createScore(c);
    }

    public void update(int alumnoId, int asignaturaId, double nota, String trimestre, String tipo) {
        Calificaciones c = new Calificaciones();
        c.setAlumnoId(alumnoId);
        c.setAsignaturaId(asignaturaId);
        c.setNota(nota);
        c.setTrimestre(trimestre);
        c.setTipo(tipo);
        dao.updateScore(c);
    }
    public List<Object[]> getAlumnos() {
        return dao.getAlumnos();
    }

    public List<Object[]> getAsignaturas() {
        return dao.getAsignaturas();
    }

    // 🔹 CONSULTAS
    public List<Calificaciones> getAll() {
        return dao.getAll();
    }

    public List<Calificaciones> getByAlumno(int alumnoId) {
        return dao.getByStudent(alumnoId);
    }

    public List<Calificaciones> getByAsignatura(int asignaturaId) {
        return dao.getBySubject(asignaturaId);
    }

    // 🔹 CÁLCULOS
    public double getNotaFinalTrimestre(int alumnoId, int asignaturaId, String trimestre) {
        return dao.getFinalGrade(alumnoId, asignaturaId, trimestre);
    }

    public double getNotaFinalGlobal(int alumnoId, int asignaturaId) {
        return dao.getGlobalFinalGrade(alumnoId, asignaturaId);
    }
}
