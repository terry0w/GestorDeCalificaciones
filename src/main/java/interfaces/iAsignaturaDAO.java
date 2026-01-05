package interfaces;

import model.Asignatura;

import java.util.List;

public interface iAsignaturaDAO {

    void create(Asignatura asignatura);
    List<Asignatura> getAll();
    Asignatura getById(int id);
    void update(Asignatura asignatura);
    void delete(int id);

}
