package view;

import controller.CursoController;
import model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CursoPanel extends AbstractManagementPanel {

    private CursoController controller;
    private JTable table;
    private DefaultTableModel model;

    public CursoPanel(MainFrame frame) {
        super(frame, "Listado de Cursos");

        controller = new CursoController();

        contentPanel.add(createTablePanel(), BorderLayout.CENTER);

        cargarCursos();
    }

    // 🔹 TABLA SOLO LECTURA
    private JScrollPane createTablePanel() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("ID");
        model.addColumn("Nombre del curso");

        table = new JTable(model);
        return new JScrollPane(table);
    }

    // 🔹 CARGAR CURSOS
    private void cargarCursos() {
        model.setRowCount(0);
        List<Curso> cursos = controller.getAll();

        for (Curso c : cursos) {
            model.addRow(new Object[]{
                    c.getId(),
                    c.getNombreCurso()
            });
        }
    }

    @Override
    protected JPanel clonePanel() {
        return new CursoPanel(frame);
    }
}
