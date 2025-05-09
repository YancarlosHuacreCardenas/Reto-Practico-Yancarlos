package view;

import controller.EstudianteController;
import model.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EstudiantesView extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JButton btnInsertar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnMostrarEliminados;
    private JTable tblEstudiantes;
    private DefaultTableModel modeloTabla;
    private JLabel lblId;
    private JTextField txtId;

    private EstudianteController controller;
    private boolean mostrandoEliminados = false;

    public EstudiantesView() {
        controller = new EstudianteController();
        inicializarComponentes();
        configurarEventos();
        listarEstudiantes();
    }

    private void inicializarComponentes() {
        setTitle("Gestión de Estudiantes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BoxLayout vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN DE ESTUDIANTES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(lblTitulo);
        panelPrincipal.add(panelTitulo);

        // Espacio
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        lblId = new JLabel("ID:");
        txtId = new JTextField();
        txtId.setEditable(false);

        panelFormulario.add(lblId);
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelFormulario.add(txtApellido);

        panelFormulario.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo);

        panelPrincipal.add(panelFormulario);

        // Espacio
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 5, 10, 0));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        btnInsertar = new JButton("Insertar");
        btnInsertar.setBackground(new Color(100, 180, 100));
        btnInsertar.setForeground(Color.WHITE);
        btnInsertar.setFont(new Font("Arial", Font.BOLD, 12));

        btnModificar = new JButton("Modificar");
        btnModificar.setBackground(new Color(100, 150, 200));
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 12));

        btnEliminar = new JButton("Eliminar Lógico");
        btnEliminar.setBackground(new Color(200, 100, 100));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));

        btnListar = new JButton("Listar");
        btnListar.setBackground(new Color(150, 150, 150));
        btnListar.setForeground(Color.WHITE);
        btnListar.setFont(new Font("Arial", Font.BOLD, 12));

        btnMostrarEliminados = new JButton("Mostrar Eliminados");
        btnMostrarEliminados.setBackground(new Color(180, 130, 180));
        btnMostrarEliminados.setForeground(Color.WHITE);
        btnMostrarEliminados.setFont(new Font("Arial", Font.BOLD, 12));

        panelBotones.add(btnInsertar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnMostrarEliminados);

        panelPrincipal.add(panelBotones);

        // Espacio
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Estudiantes"));

        // Tabla
        String[] columnas = {"ID", "Nombre", "Apellido", "Correo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };

        tblEstudiantes = new JTable(modeloTabla);
        tblEstudiantes.getTableHeader().setReorderingAllowed(false);
        tblEstudiantes.getTableHeader().setBackground(new Color(70, 130, 180));
        tblEstudiantes.getTableHeader().setForeground(Color.WHITE);
        tblEstudiantes.setRowHeight(25);
        tblEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblEstudiantes);
        scrollPane.setPreferredSize(new Dimension(750, 200));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelTabla);

        // Agregar panel principal al frame
        add(panelPrincipal);
    }

    private void configurarEventos() {
        // Evento para insertar
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarEstudiante();
            }
        });

        // Evento para modificar
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarEstudiante();
            }
        });

        // Evento para eliminar lógicamente
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });

        // Evento para listar
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrandoEliminados = false;
                listarEstudiantes();
            }
        });

        // Evento para mostrar eliminados
        btnMostrarEliminados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrandoEliminados = true;
                listarEstudiantesEliminados();
            }
        });

        // Evento para seleccionar un registro de la tabla
        tblEstudiantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblEstudiantes.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    seleccionarEstudiante(filaSeleccionada);
                }
            }
        });
    }

    private void insertarEstudiante() {
        // Validar campos
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insertar estudiante
        if (controller.insertarEstudiante(txtNombre.getText(), txtApellido.getText(), txtCorreo.getText())) {
            JOptionPane.showMessageDialog(this, "Estudiante insertado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            listarEstudiantes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar estudiante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarEstudiante() {
        // Validar que se haya seleccionado un registro
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un estudiante para modificar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar campos
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Modificar estudiante
        int id = Integer.parseInt(txtId.getText());
        if (controller.modificarEstudiante(id, txtNombre.getText(), txtApellido.getText(), txtCorreo.getText())) {
            JOptionPane.showMessageDialog(this, "Estudiante modificado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            listarEstudiantes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar estudiante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEstudiante() {
        // Validar que se haya seleccionado un registro
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un estudiante para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirmar eliminación
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este estudiante?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(txtId.getText());

            // Eliminar lógicamente
            if (controller.eliminarEstudiante(id)) {
                JOptionPane.showMessageDialog(this, "Estudiante eliminado lógicamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                listarEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listarEstudiantes() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        try {
            // Obtener lista de estudiantes activos
            List<Estudiante> estudiantes = controller.listarEstudiantesActivos();

            // Llenar tabla
            for (Estudiante estudiante : estudiantes) {
                Object[] fila = {
                        estudiante.getId(),
                        estudiante.getNombre(),
                        estudiante.getApellido(),
                        estudiante.getCorreo(),
                        estudiante.isEstado() ? "Activo" : "Inactivo"
                };
                modeloTabla.addRow(fila);
            }

            // Actualizar título de la ventana
            setTitle("Gestión de Estudiantes - Mostrando Activos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al listar estudiantes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarEstudiantesEliminados() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        try {
            // Obtener lista de estudiantes eliminados
            List<Estudiante> estudiantes = controller.listarEstudiantesEliminados();

            // Llenar tabla
            for (Estudiante estudiante : estudiantes) {
                Object[] fila = {
                        estudiante.getId(),
                        estudiante.getNombre(),
                        estudiante.getApellido(),
                        estudiante.getCorreo(),
                        estudiante.isEstado() ? "Activo" : "Inactivo"
                };
                modeloTabla.addRow(fila);
            }

            // Actualizar título de la ventana
            setTitle("Gestión de Estudiantes - Mostrando Eliminados");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al listar estudiantes eliminados: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void seleccionarEstudiante(int fila) {
        // Obtener datos de la fila seleccionada
        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nombre = (String) modeloTabla.getValueAt(fila, 1);
        String apellido = (String) modeloTabla.getValueAt(fila, 2);
        String correo = (String) modeloTabla.getValueAt(fila, 3);

        // Llenar campos del formulario
        txtId.setText(String.valueOf(id));
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtCorreo.setText(correo);
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        tblEstudiantes.clearSelection();
    }
}
