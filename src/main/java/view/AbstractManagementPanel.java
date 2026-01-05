package view;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractManagementPanel extends JPanel {

    protected MainFrame frame;
    protected JPanel contentPanel;

    public AbstractManagementPanel(MainFrame frame, String title) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // TÍTULO
        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitle, BorderLayout.NORTH);

        // CONTENIDO (para los hijos)
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // BOTONES FIJOS
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        JButton btnBack = new JButton("Volver al menú");
        JButton btnNewWindow = new JButton("Abrir en nueva ventana");

        btnBack.addActionListener(e -> frame.showPanel(MainFrame.MENU));
        btnNewWindow.addActionListener(e -> openInNewWindow());

        panel.add(btnBack);
        panel.add(btnNewWindow);

        return panel;
    }

    private void openInNewWindow() {
        JDialog dialog = new JDialog(frame, "Vista independiente", true);
        dialog.setSize(700, 450);
        dialog.setLocationRelativeTo(frame);
        dialog.add(clonePanel());
        dialog.setVisible(true);
    }

    protected abstract JPanel clonePanel();
}
