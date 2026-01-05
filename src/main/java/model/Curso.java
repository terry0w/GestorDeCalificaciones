package model;


public class Curso {
    private int id;
    private String nombreCurso;

    public Curso() {}
    public Curso(int id, String nombreCurso) {
        this.id = id;
        this.nombreCurso = nombreCurso;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }

    @Override
    public String toString() {
        return "Curso [id=" + id + ", nombreCurso=" + nombreCurso + "]";
    }
}