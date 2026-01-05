package view;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(MainFrame frame) {
        setLayout(new GridLayout(2, 2, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton alumnosBtn = new JButton("Alumnos");
        JButton cursosBtn = new JButton("Cursos");
        JButton asignaturasBtn = new JButton("Asignaturas");
        JButton calificacionesBtn = new JButton("Calificaciones");

        alumnosBtn.addActionListener(e -> frame.showPanel(MainFrame.ALUMNOS));
        cursosBtn.addActionListener(e -> frame.showPanel(MainFrame.CURSOS));
        asignaturasBtn.addActionListener(e -> frame.showPanel(MainFrame.ASIGNATURAS));
        calificacionesBtn.addActionListener(e -> frame.showPanel(MainFrame.CALIFICACIONES));

        add(alumnosBtn);
        add(cursosBtn);
        add(asignaturasBtn);
        add(calificacionesBtn);
    }
}
