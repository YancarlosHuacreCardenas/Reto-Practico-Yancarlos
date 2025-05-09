import database.ConexionDB;
import view.EstudiantesView;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        // Configurar look and feel para una mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Verificar la conexión a la base de datos antes de iniciar la aplicación
        if (!ConexionDB.testConnection()) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo establecer conexión con la base de datos.\n" +
                            "Por favor, verifique que:\n" +
                            "1. El servidor MySQL esté en ejecución\n" +
                            "2. Las credenciales sean correctas\n" +
                            "3. La base de datos 'student_management' exista\n\n" +
                            "La aplicación se cerrará.",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Iniciar la aplicación en el EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EstudiantesView view = new EstudiantesView();
                view.setVisible(true);
            }
        });
    }
}
