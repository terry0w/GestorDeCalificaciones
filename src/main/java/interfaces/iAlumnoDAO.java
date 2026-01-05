package interfaces;

import model.Alumno;

import java.util.List;

public interface iAlumnoDAO {
    void create(Alumno alumno);
    List<Alumno> getAll();
    List<Alumno> getByNif(String nif);
    void update(Alumno alumno);
    void delete(int id);

}
