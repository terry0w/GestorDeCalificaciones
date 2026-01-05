package model;

public class Calificaciones {
    private int id;
    private int alumnoId;
    private int asignaturaId;
    private double nota;
    private String trimestre;
    private String tipo; // "Práctico" o "Examen"

    public Calificaciones() {}

    public Calificaciones(int id, int alumnoId, int asignaturaId, double nota, String trimestre, String tipo) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
        this.nota = nota;
        this.trimestre = trimestre;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAlumnoId() { return alumnoId; }
    public void setAlumnoId(int alumnoId) { this.alumnoId = alumnoId; }

    public int getAsignaturaId() { return asignaturaId; }
    public void setAsignaturaId(int asignaturaId) { this.asignaturaId = asignaturaId; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public String getTrimestre() { return trimestre; }
    public void setTrimestre(String trimestre) { this.trimestre = trimestre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "Calificacion [id=" + id + ", alumnoId=" + alumnoId +
                ", asignaturaId=" + asignaturaId + ", nota=" + nota +
                ", trimestre=" + trimestre + ", tipo=" + tipo + "]";
    }
}