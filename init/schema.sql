-- Tabla de usuarios
CREATE TABLE users (
    id VARCHAR(100) PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    name VARCHAR(100),
    lastname VARCHAR(100),
    password VARCHAR(100),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de trabajos (tareas o proyectos principales)
CREATE TABLE works (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100),
    user_id VARCHAR(100),
    description VARCHAR(255),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Grupos de tareas asociadas a trabajos
CREATE TABLE tasks_groups (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100),
    visible TINYINT,
    restriction TINYINT,
    work_id VARCHAR(100),
    FOREIGN KEY (work_id) REFERENCES works(id)
);

-- Tareas individuales asignadas a usuarios
CREATE TABLE tasks (
    id VARCHAR(100) PRIMARY KEY,
    description VARCHAR(255),
    state TINYINT DEFAULT FALSE,
    user_id VARCHAR(100),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    limit_date DATETIME DEFAULT NULL,
    task_group_id VARCHAR(100),
    work_id VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (task_group_id) REFERENCES tasks_groups(id),
    FOREIGN KEY (work_id) REFERENCES works(id)
);

-- Permisos disponibles para grupos
CREATE TABLE group_permissions (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100)
);

-- Permisos disponibles para trabajos
CREATE TABLE works_permissions (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100)
);

-- Participación de usuarios en trabajos con permisos específicos
CREATE TABLE participate (
    user_id VARCHAR(100),
    work_id VARCHAR(100),
    permission_id INTEGER,
    PRIMARY KEY (user_id, work_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (work_id) REFERENCES works(id),
    FOREIGN KEY (permission_id) REFERENCES works_permissions(id)
);

-- Asignación de usuarios a grupos con permisos específicos
CREATE TABLE assigned (
    user_id VARCHAR(100),
    group_id VARCHAR(100),
    permission_id INTEGER,
    PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (group_id) REFERENCES tasks_groups(id),
    FOREIGN KEY (permission_id) REFERENCES group_permissions(id)
);