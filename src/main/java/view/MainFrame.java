package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final String MENU = "menu";
    public static final String ALUMNOS = "alumnos";
    public static final String CURSOS = "cursos";
    public static final String ASIGNATURAS = "asignaturas";
    public static final String CALIFICACIONES = "calificaciones";

    private CardLayout cardLayout;
    private JPanel container;

    public MainFrame() {
        setTitle("Gestión Escolar");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        //paneles
        container.add(new MainMenuPanel(this), MENU);
        container.add(new AlumnoPanel(this), ALUMNOS);
        container.add(new CursoPanel(this), CURSOS);
        container.add(new AsignaturaPanel(this), ASIGNATURAS);
        container.add(new CalificacionesPanel(this), CALIFICACIONES);

        add(container);
        showPanel(MENU);
    }

    public void showPanel(String name) {
        cardLayout.show(container, name);
    }

}
