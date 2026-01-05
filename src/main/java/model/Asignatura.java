package model;

public class Asignatura {
    private int id;
    private String nombreAsignatura;
    private int cursoId;
    private double valorPractico;
    private double valorExamen;

    public Asignatura() {
        this.valorPractico = 0.3;
        this.valorExamen = 0.7;
    }

    public Asignatura(int id, String nombreAsignatura, int cursoId, double valorPractico, double valorExamen) {
        this.id = id;
        this.nombreAsignatura = nombreAsignatura;
        this.cursoId = cursoId;
        this.valorPractico = valorPractico;
        this.valorExamen = valorExamen;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreAsignatura() { return nombreAsignatura; }
    public void setNombreAsignatura(String nombreAsignatura) { this.nombreAsignatura = nombreAsignatura; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }

    public double getValorPractico() { return valorPractico; }
    public void setValorPractico(double valorPractico) { this.valorPractico = valorPractico; }

    public double getValorExamen() { return valorExamen; }
    public void setValorExamen(double valorExamen) { this.valorExamen = valorExamen; }

    @Override
    public String toString() {
        return "Asignatura [id=" + id + ", nombreAsignatura=" + nombreAsignatura +
                ", cursoId=" + cursoId + ", valorPractico=" + valorPractico +
                ", valorExamen=" + valorExamen + "]";
    }
}