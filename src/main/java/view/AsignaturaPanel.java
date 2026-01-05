package view;

import controller.AsignaturaController;
import model.Asignatura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AsignaturaPanel extends AbstractManagementPanel {

    private AsignaturaController controller;
    private JTable table;
    private DefaultTableModel model;

    private JTextField txtNombre, txtCurso, txtPractico, txtExamen;
    private JTextField txtEliminarId;

    public AsignaturaPanel(MainFrame frame) {
        super(frame, "Gestión de Asignaturas");

        controller = new AsignaturaController();

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(createFormPanel(), BorderLayout.NORTH);
        southPanel.add(createDeletePanel(), BorderLayout.SOUTH);

        contentPanel.add(createTablePanel(), BorderLayout.CENTER);
        contentPanel.add(southPanel, BorderLayout.SOUTH);

        cargarAsignaturas();
    }

    // 🔹 TABLA
    private JScrollPane createTablePanel() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Curso ID");
        model.addColumn("Práctico");
        model.addColumn("Examen");

        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(e -> cargarSeleccion());

        return new JScrollPane(table);
    }

    // 🔹 FORMULARIO CREAR / EDITAR
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 6, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Alta / Edición"));

        txtNombre = new JTextField();
        txtCurso = new JTextField();
        txtPractico = new JTextField("0.30");
        txtExamen = new JTextField("0.70");

        JButton btnGuardar = new JButton("Guardar / Actualizar");

        btnGuardar.addActionListener(e -> guardarAsignatura());

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Curso ID:"));
        panel.add(txtCurso);
        panel.add(new JLabel("Práctico:"));
        panel.add(txtPractico);

        panel.add(new JLabel("Examen:"));
        panel.add(txtExamen);
        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        return panel;
    }

    // 🔹 PANEL ELIMINAR
    private JPanel createDeletePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Eliminar asignatura"));

        txtEliminarId = new JTextField(5);
        JButton btnEliminar = new JButton("Eliminar por ID");

        btnEliminar.addActionListener(e -> eliminarAsignaturaPorId());

        panel.add(new JLabel("ID:"));
        panel.add(txtEliminarId);
        panel.add(btnEliminar);

        return panel;
    }

    // 🔹 CARGAR TABLA
    private void cargarAsignaturas() {
        model.setRowCount(0);
        List<Asignatura> lista = controller.getAll();

        for (Asignatura a : lista) {
            model.addRow(new Object[]{
                    a.getId(),
                    a.getNombreAsignatura(),
                    a.getCursoId(),
                    a.getValorPractico(),
                    a.getValorExamen()
            });
        }
    }

    // 🔹 CARGAR SELECCIÓN PARA EDITAR
    private void cargarSeleccion() {
        int fila = table.getSelectedRow();
        if (fila == -1) return;

        txtNombre.setText(model.getValueAt(fila, 1).toString());
        txtCurso.setText(model.getValueAt(fila, 2).toString());
        txtPractico.setText(model.getValueAt(fila, 3).toString());
        txtExamen.setText(model.getValueAt(fila, 4).toString());
    }

    // 🔹 GUARDAR / ACTUALIZAR
    private void guardarAsignatura() {
        try {
            int fila = table.getSelectedRow();

            String nombre = txtNombre.getText();
            int curso = Integer.parseInt(txtCurso.getText());
            double practico = Double.parseDouble(txtPractico.getText());
            double examen = Double.parseDouble(txtExamen.getText());

            if (practico + examen != 1.0) {
                JOptionPane.showMessageDialog(this,
                        "La suma de práctico y examen debe ser 1",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (fila == -1) {
                controller.create(nombre, curso, practico, examen);
            } else {
                int id = (int) model.getValueAt(fila, 0);
                controller.update(id, nombre, curso, practico, examen);
            }

            limpiarFormulario();
            cargarAsignaturas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🔹 ELIMINAR POR ID
    private void eliminarAsignaturaPorId() {
        try {
            int id = Integer.parseInt(txtEliminarId.getText());

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que quieres eliminar la asignatura con ID " + id + "?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.delete(id);
                cargarAsignaturas();
                txtEliminarId.setText("");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtCurso.setText("");
        txtPractico.setText("0.30");
        txtExamen.setText("0.70");
        table.clearSelection();
    }

    @Override
    protected JPanel clonePanel() {
        return new AsignaturaPanel(frame);
    }
}
