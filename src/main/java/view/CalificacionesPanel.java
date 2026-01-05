package view;

import controller.CalificacionesController;
import model.Calificaciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CalificacionesPanel extends AbstractManagementPanel {

    private CalificacionesController controller;
    private JTable table;
    private DefaultTableModel model;

    private JTextField txtAlumnoId, txtAsignaturaId, txtNota;
    private JComboBox<String> cbTrimestre, cbTipo;

    public CalificacionesPanel(MainFrame frame) {
        super(frame, "Gestión de Calificaciones");

        controller = new CalificacionesController();

        contentPanel.add(createTopPanel(), BorderLayout.NORTH);
        contentPanel.add(createTablePanel(), BorderLayout.CENTER);
        contentPanel.add(createFormPanel(), BorderLayout.SOUTH);

        cargarTodas();
    }

    // 🔹 FILTROS
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();

        JTextField txtFiltroAlumno = new JTextField(5);
        JTextField txtFiltroAsignatura = new JTextField(5);

        JButton btnAlumno = new JButton("Por alumno");
        btnAlumno.addActionListener(e ->
                cargarPorAlumno(Integer.parseInt(txtFiltroAlumno.getText())));

        JButton btnAsignatura = new JButton("Por asignatura");
        btnAsignatura.addActionListener(e ->
                cargarPorAsignatura(Integer.parseInt(txtFiltroAsignatura.getText())));

        JButton btnTodas = new JButton("Ver todas");
        btnTodas.addActionListener(e -> cargarTodas());

        panel.add(new JLabel("Alumno ID:"));
        panel.add(txtFiltroAlumno);
        panel.add(btnAlumno);

        panel.add(new JLabel("Asignatura ID:"));
        panel.add(txtFiltroAsignatura);
        panel.add(btnAsignatura);

        panel.add(btnTodas);

        return panel;
    }

    // 🔹 TABLA
    private JScrollPane createTablePanel() {
        model = new DefaultTableModel();
        model.addColumn("Alumno ID");
        model.addColumn("Asignatura ID");
        model.addColumn("Nota");
        model.addColumn("Trimestre");
        model.addColumn("Tipo");

        table = new JTable(model);
        return new JScrollPane(table);
    }

    // 🔹 FORMULARIO
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 6, 5, 5));

        txtAlumnoId = new JTextField();
        txtAsignaturaId = new JTextField();
        txtNota = new JTextField();

        cbTrimestre = new JComboBox<>(new String[]{"1", "2", "3"});
        cbTipo = new JComboBox<>(new String[]{"Práctico", "Examen"});

        JButton btnGuardar = new JButton("Guardar / Actualizar");
        btnGuardar.addActionListener(e -> guardar());

        panel.add(new JLabel("Alumno ID"));
        panel.add(txtAlumnoId);
        panel.add(new JLabel("Asignatura ID"));
        panel.add(txtAsignaturaId);
        panel.add(new JLabel("Nota"));
        panel.add(txtNota);

        panel.add(new JLabel("Trimestre"));
        panel.add(cbTrimestre);
        panel.add(new JLabel("Tipo"));
        panel.add(cbTipo);
        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        return panel;
    }

    // 🔹 CARGAS
    private void cargarTodas() {
        cargar(controller.getAll());
    }

    private void cargarPorAlumno(int id) {
        cargar(controller.getByAlumno(id));
    }

    private void cargarPorAsignatura(int id) {
        cargar(controller.getByAsignatura(id));
    }

    private void cargar(List<Calificaciones> lista) {
        model.setRowCount(0);
        for (Calificaciones c : lista) {
            model.addRow(new Object[]{
                    c.getAlumnoId(),
                    c.getAsignaturaId(),
                    c.getNota(),
                    c.getTrimestre(),
                    c.getTipo()
            });
        }
    }

    // 🔹 GUARDAR
    private void guardar() {
        try {
            controller.create(
                    Integer.parseInt(txtAlumnoId.getText()),
                    Integer.parseInt(txtAsignaturaId.getText()),
                    Double.parseDouble(txtNota.getText()),
                    cbTrimestre.getSelectedItem().toString(),
                    cbTipo.getSelectedItem().toString()
            );
            cargarTodas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar calificación");
        }
    }

    @Override
    protected JPanel clonePanel() {
        return new CalificacionesPanel(frame);
    }
}
