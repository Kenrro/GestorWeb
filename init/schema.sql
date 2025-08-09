-- Tabla de usuarios
CREATE TABLE users (
    id VARCHAR(100) PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de trabajos (tareas o proyectos principales)
CREATE TABLE works (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    user_id VARCHAR(100),
    description VARCHAR(255),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_user_name UNIQUE (name, user_id),
    CONSTRAINT unique_id UNIQUE(id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Grupos de tareas asociadas a trabajos
CREATE TABLE tasks_groups (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    visible TINYINT,
    restriction TINYINT,
    work_id VARCHAR(100),
    FOREIGN KEY (work_id) REFERENCES works(id) ON DELETE CASCADE
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

insert into group_permissions (id, name) values (1, "Administration");
insert into group_permissions (id, name) values (2, "Edit");
insert into group_permissions (id, name) values (3, "Write");
insert into group_permissions (id, name) values (4, "Read");

-- Permisos disponibles para trabajos
CREATE TABLE works_permissions (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100)
);
insert into works_permissions (id, name) values (1, "Administration");
insert into works_permissions (id, name) values (2, "Edit");
insert into works_permissions (id, name) values (3, "Write");
insert into works_permissions (id, name) values (4, "Read");

-- Participación de usuarios en trabajos con permisos específicos
CREATE TABLE participate (
    user_id VARCHAR(100),
    work_id VARCHAR(100),
    permission_id INTEGER,
    PRIMARY KEY (user_id, work_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (work_id) REFERENCES works(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES works_permissions(id)
);

-- Asignación de usuarios a grupos con permisos específicos
CREATE TABLE assigned (
    user_id VARCHAR(100),
    group_id VARCHAR(100),
    permission_id INTEGER,
    PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES tasks_groups(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES group_permissions(id)
);