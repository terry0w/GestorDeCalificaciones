package interfaces;

import model.Curso;

import java.util.List;

public interface iCursoDAO {

    void create(Curso curso);
    List<Curso> getAll();
    Curso getById(int id);
    void update(Curso curso);
    void delete(int id);

}
