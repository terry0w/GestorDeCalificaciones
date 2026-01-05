package view;

import controller.AlumnoController;
import model.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AlumnoPanel extends AbstractManagementPanel {

    private AlumnoController controller;
    private JTable table;
    private DefaultTableModel model;

    private JTextField txtNif, txtNombre, txtEmail, txtBuscar, txtCurso;
    private JTextField txtEliminarId;

    public AlumnoPanel(MainFrame frame) {
        super(frame, "Gestión de Alumnos");

        controller = new AlumnoController();

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(createFormPanel(), BorderLayout.NORTH);
        southPanel.add(createDeletePanel(), BorderLayout.SOUTH);

        contentPanel.add(createTopPanel(), BorderLayout.NORTH);
        contentPanel.add(createTablePanel(), BorderLayout.CENTER);
        contentPanel.add(southPanel, BorderLayout.SOUTH);

        cargarAlumnos();
    }

    // 🔹 PANEL SUPERIOR (BÚSQUEDA)
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();

        panel.add(new JLabel("Buscar por NIF:"));
        txtBuscar = new JTextField(15);
        panel.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarAlumno());
        panel.add(btnBuscar);

        JButton btnTodos = new JButton("Ver todos");
        btnTodos.addActionListener(e -> cargarAlumnos());
        panel.add(btnTodos);

        return panel;
    }

    // 🔹 TABLA
    private JScrollPane createTablePanel() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NIF");
        model.addColumn("Nombre");
        model.addColumn("Email");
        model.addColumn("Curso ID");

        table = new JTable(model);
        return new JScrollPane(table);
    }

    // 🔹 FORMULARIO CREAR
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 5, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Alta de alumno"));

        txtNif = new JTextField();
        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtCurso = new JTextField();

        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(e -> guardarAlumno());

        panel.add(new JLabel("NIF:"));
        panel.add(txtNif);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Curso ID:"));
        panel.add(txtCurso);
        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        return panel;
    }

    // 🔹 PANEL ELIMINAR
    private JPanel createDeletePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Eliminar alumno"));

        txtEliminarId = new JTextField(5);
        JButton btnEliminar = new JButton("Eliminar por ID");

        btnEliminar.addActionListener(e -> deleteStudentById());

        panel.add(new JLabel("ID:"));
        panel.add(txtEliminarId);
        panel.add(btnEliminar);

        return panel;
    }

    // 🔹 CARGAR TODOS
    private void cargarAlumnos() {
        model.setRowCount(0);
        List<Alumno> alumnos = controller.getAll();

        for (Alumno a : alumnos) {
            model.addRow(new Object[]{
                    a.getId(),
                    a.getNif(),
                    a.getNombreAlumno(),
                    a.getEmail(),
                    a.getCursoId()
            });
        }
    }

    // 🔹 BUSCAR
    private void buscarAlumno() {
        model.setRowCount(0);
        List<Alumno> alumnos = controller.getByNif(txtBuscar.getText());

        for (Alumno a : alumnos) {
            model.addRow(new Object[]{
                    a.getId(),
                    a.getNif(),
                    a.getNombreAlumno(),
                    a.getEmail(),
                    a.getCursoId()
            });
        }
    }

    // 🔹 GUARDAR
    private void guardarAlumno() {
        String nif = txtNif.getText();
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String cursoTexto = txtCurso.getText();

        if (nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos obligatorios");
            return;
        }

        try {
            int curso = Integer.parseInt(cursoTexto);
            controller.create(nif, nombre, email, curso);
            cargarAlumnos();

            txtNif.setText("");
            txtNombre.setText("");
            txtEmail.setText("");
            txtCurso.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El curso debe ser numérico");
        }
    }

    // 🔹 ELIMINAR POR ID
    private void deleteStudentById() {
        try {
            int id = Integer.parseInt(txtEliminarId.getText());

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que quieres eliminar el alumno con ID " + id + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.delete(id);
                cargarAlumnos();
                txtEliminarId.setText("");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    // 🔹 NECESARIO PARA HACER NUEVA VENTANA
    @Override
    protected JPanel clonePanel() {
        return new AlumnoPanel(frame);
    }
}
