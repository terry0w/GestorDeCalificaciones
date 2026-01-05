package model;

public class Alumno {
    private int id;
    private String nif;
    private String nombreAlumno;
    private String email;
    private int cursoId; // referencia al curso

    public Alumno() {}
    public Alumno(String nif, String nombreAlumno, String email, int cursoId){
        this.nif = nif;
        this.nombreAlumno = nombreAlumno;
        this.email = email;
        this.cursoId = cursoId;
    }
    public Alumno(int id, String nif, String nombreAlumno, String email, int cursoId) {
        this.id = id;
        this.nif = nif;
        this.nombreAlumno = nombreAlumno;
        this.email = email;
        this.cursoId = cursoId;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNif() { return  nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getNombreAlumno() { return nombreAlumno; }
    public void setNombreAlumno(String nombreAlumno) { this.nombreAlumno = nombreAlumno; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }

    @Override
    public String toString() {
        return "Alumno [id=" + id + ","+ "nif=" + nif + " nombreAlumno=" + nombreAlumno + ", email=" + email + ", cursoId=" + cursoId + "]";
    }
}