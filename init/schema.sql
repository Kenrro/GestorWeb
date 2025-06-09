CREATE TABLE usuarios (
    id VARCHAR(100) PRIMARY KEY,
    nombre VARCHAR(100) unique,
    password VARCHAR(100)
);

CREATE TABLE tareas (
    id VARCHAR(100) PRIMARY KEY,
    descripcion VARCHAR(255),
    completada BOOLEAN default FALSE,
    usuario_id VARCHAR(100),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);