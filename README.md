# Aplicación de Gestión de Hotel

La Aplicación de Gestión de Hotel es una aplicación de escritorio basada en Java que te permite gestionar reservas de hoteles e información de huéspedes de forma sencilla y eficiente.

## Tabla de Contenido

- [Características](#características)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Contribuciones](#contribuciones)

## Características

- **Gestión de Reservas:** Permite a los usuarios crear, editar y eliminar reservas de hotel.
- **Gestión de Huéspedes:** Proporciona funcionalidad para gestionar la información de huéspedes asociada con las reservas.
- **Funcionalidad de Búsqueda:** Permite a los usuarios buscar reservas y huéspedes.
- **Interfaz de Usuario Amigable:** Utiliza una interfaz gráfica de usuario (GUI) para facilitar su uso.

## Requisitos Previos

Antes de comenzar, asegúrate de cumplir con los siguientes requisitos:

- **Kit de Desarrollo de Java (JDK):** Debes tener el JDK instalado en tu sistema.
- **Base de Datos MySQL:** Asegúrate de tener una base de datos MySQL configurada para almacenar la información de las reservas y los huéspedes.

## Instalación

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone https://github.com/tu-nombre-de-usuario/aplicacion-gestion-hotel.git
2. Importa el proyecto en tu entorno de desarrollo Java preferido (por ejemplo, Eclipse, IntelliJ IDEA).

3. Configura la conexión a la base de datos MySQL en la clase ConexionMySql. Actualiza las variables URL, USUARIO y CONTRASEÑA para que coincidan con la configuración de tu servidor MySQL.

4. Copia las librerías JAR necesarias desde la carpeta "Jars" a la carpeta de dependencias de tu proyecto.

5. Compila y ejecuta la aplicación desde tu entorno de desarrollo.

## Uso
1. Inicia la Aplicación de Gestión de Hotel.

2. Utiliza la interfaz gráfica de usuario (GUI) para gestionar reservas de hotel e información de huéspedes.

3. Busca reservas y huéspedes utilizando la función de búsqueda.

4. Edita o elimina reservas y huéspedes según sea necesario.

## Contribuciones
¡Las contribuciones son bienvenidas! Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.

2. Crea una nueva rama para tu funcionalidad o corrección de errores:

  git checkout -b feature/nombre-de-tu-funcionalidad

3. Realiza tus cambios y haz un commit en tu repositorio fork:
  git commit -m "Añadir tu funcionalidad o corrección de errores"
  git push origin feature/nombre-de-tu-funcionalidad

Crea una solicitud de extracción hacia el repositorio principal.

## Creacion de la Base de Datos en MySql

-- Crear la base de datos bd_hotel
CREATE DATABASE db_hotel;

-- Usar la base de datos bd_hotel
USE db_hotel;

-- Crear la tabla 'huespedes'
CREATE TABLE huespedes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) COLLATE utf8mb4_general_ci,
    Apellido VARCHAR(255) COLLATE utf8mb4_general_ci,
    FechaNacimiento DATE,
    Nacionalidad VARCHAR(255) COLLATE utf8mb4_general_ci,
    Telefono VARCHAR(20) COLLATE utf8mb4_general_ci,
    idReserva INT,
    FOREIGN KEY (idReserva) REFERENCES reserva(id)
);

-- Crear la tabla 'reserva'
CREATE TABLE reserva (
    id INT PRIMARY KEY,
    FechaEntrada DATE,
    FechaSalida DATE,
    Valor DECIMAL(10, 2),
    FormaPago VARCHAR(50) COLLATE utf8mb4_general_ci
);

-- Crear la tabla 'usuarios'
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) COLLATE utf8mb4_general_ci,
    contraseña VARCHAR(50) COLLATE utf8mb4_general_ci
);
