package controller;

import dao.CursoDAO;
import model.Curso;

import java.util.List;

public class CursoController {

    private CursoDAO dao;

    public CursoController() {
        dao = new CursoDAO();
    }

    public List<Curso> getAll() {
        return dao.getAll();
    }

    //DESHABILITADOS POR INTEGRIDAD REFERENCIAL
    public void create(String nombre) {
        // vacío
    }

    public void update(int id, String nombre) {
        // vacío
    }

    public void delete(int id) {
        // vacío
    }
}
