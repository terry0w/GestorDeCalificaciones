package controller;

import dao.AsignaturaDAO;
import model.Asignatura;

import java.util.List;

public class AsignaturaController {

    private AsignaturaDAO dao;

    public AsignaturaController() {
        dao = new AsignaturaDAO();
    }

    public void create(String nombre, int cursoId, double valorPractico, double valorExamen) {
        Asignatura a = new Asignatura();
        a.setNombreAsignatura(nombre);
        a.setCursoId(cursoId);
        a.setValorPractico(valorPractico);
        a.setValorExamen(valorExamen);

        dao.create(a);
    }

    public List<Asignatura> getAll() {
        return dao.getAll();
    }

    public Asignatura getById(int id) {
        return dao.getById(id);
    }

    public void update(int id, String nombre, int cursoId, double valorPractico, double valorExamen) {
        Asignatura a = new Asignatura();
        a.setId(id);
        a.setNombreAsignatura(nombre);
        a.setCursoId(cursoId);
        a.setValorPractico(valorPractico);
        a.setValorExamen(valorExamen);

        dao.update(a);
    }

    public void delete(int id) {
        dao.delete(id);
    }
}
