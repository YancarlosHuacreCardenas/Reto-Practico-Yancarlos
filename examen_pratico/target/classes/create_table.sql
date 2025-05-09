CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE IF NOT EXISTS estudiantes (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    estado TINYINT(1) DEFAULT 1
    );

-- Insertar algunos datos de ejemplo (opcional)
INSERT INTO estudiantes (nombre, apellido, correo, estado) VALUES
                                                               ('Juan', 'Pérez', 'juan.perez@ejemplo.com', 1),
                                                               ('María', 'González', 'maria.gonzalez@ejemplo.com', 1),
                                                               ('Carlos', 'Rodríguez', 'carlos.rodriguez@ejemplo.com', 1);
