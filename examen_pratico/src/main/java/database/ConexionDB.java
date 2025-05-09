package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionDB {
    // Configuración de la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:33060/student_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Yancarlos"; // Cambia esto según tu configuración

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Intentar establecer la conexión
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida correctamente");
            return conn;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Error: Driver MySQL no encontrado.\n" + e.getMessage(),
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
            throw new SQLException("MySQL JDBC Driver no encontrado", e);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error de conexión a la base de datos:\n" + e.getMessage() +
                            "\n\nVerifique que:\n" +
                            "1. El servidor MySQL esté en ejecución\n" +
                            "2. Las credenciales sean correctas\n" +
                            "3. La base de datos 'student_management' exista",
                    "Error de Conexión",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    // Método para probar la conexión
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
