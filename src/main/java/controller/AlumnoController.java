package controller;

import dao.AlumnoDAO;
import model.Alumno;

import java.awt.*;
import java.util.List;

public class AlumnoController {

    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public void create(String nif, String nombre, String email, int cursoId) {
        Alumno alumno = new Alumno(nif, nombre, email, cursoId);
        alumnoDAO.create(alumno);
    }
    public void delete(int id){
        alumnoDAO.delete(id);
    }
    public List<Alumno> getAll() {
        return alumnoDAO.getAll();
    }

    public List<Alumno> getByNif(String nif) {
        return alumnoDAO.getByNif(nif);
    }
}
