package interfaces;

import model.Calificaciones;
import java.util.List;

public interface iCalificacionesDAO {

    void createScore(Calificaciones calificacion);

    List<Calificaciones> getAll();
    List<Calificaciones> getBySubject(int asignaturaId);
    List<Calificaciones> getByStudent(int alumnoId);
    List<String> getAllStudentNames();

    void updateScore(Calificaciones calificacion);

    double getFinalGrade(int alumnoId, int asignaturaId, String trimestre);

    double getGlobalFinalGrade(int alumnoId, int asignaturaId);

    void delete(int id);
}