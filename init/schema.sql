CREATE TABLE usuarios (
    id VARCHAR(100) PRIMARY KEY,
    nombre VARCHAR(100) unique,
    password VARCHAR(100)
);

CREATE TABLE tareas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255),
    completada BOOLEAN,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);