-- Tabla Hospital
CREATE TABLE Hospital (
    hospital_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255)
);

-- Tabla Cuarto
CREATE TABLE Cuarto (
    cuarto_id INT PRIMARY KEY AUTO_INCREMENT,
    numero_cuarto INT NOT NULL,
    hospital_id INT,
    FOREIGN KEY (hospital_id) REFERENCES Hospital(hospital_id)
);

-- Tabla Persona (Tabla generalizada para Paciente y Doctor)
CREATE TABLE Persona (
    persona_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    nro_seguro_social VARCHAR(15) NOT NULL UNIQUE,
    es_doctor BOOLEAN NOT NULL,
    es_paciente BOOLEAN NOT NULL
);

-- Tabla Doctor (Hereda de Persona)
CREATE TABLE Doctor (
    persona_id INT PRIMARY KEY,
    especialidad VARCHAR(100),
    FOREIGN KEY (persona_id) REFERENCES Persona(persona_id)
);

-- Tabla Paciente (Hereda de Persona)
CREATE TABLE Paciente (
    persona_id INT PRIMARY KEY,
    edad INT,
    FOREIGN KEY (persona_id) REFERENCES Persona(persona_id)
);

-- Tabla Operación
CREATE TABLE Operacion (
    operacion_id INT PRIMARY KEY AUTO_INCREMENT,
    fecha_hora DATETIME NOT NULL,
    paciente_id INT,
    doctor_id INT,
    cuarto_id INT,
    FOREIGN KEY (paciente_id) REFERENCES Paciente(persona_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(persona_id),
    FOREIGN KEY (cuarto_id) REFERENCES Cuarto(cuarto_id)
);