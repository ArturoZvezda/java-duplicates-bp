# Java Duplicates Finder

Este proyecto es una aplicación en Java que lee un archivo Excel (en formato `.xlsx`) y detecta duplicados en los datos. Utiliza Apache POI para manejar el archivo Excel y procesa los datos de manera eficiente.

## Requisitos

- **JDK 11 o superior**.
- **Apache POI**: Para la manipulación de archivos Excel.

## Estructura del Proyecto

- **`src/main/java/org/example`**: Contiene el código fuente del proyecto.
    - **`Main.java`**: Clase principal que ejecuta la aplicación.
    - **`DuplicateDetector.java`**: Contiene la lógica encontrar duplicados.
    - **`ExcelReader.java`**: Contiene la logica para leer el archivo Excel.
    - **`data`**: Contiene el archivo de datos `input.xlsx` que debe ser procesado por la aplicación.

## Dependencias

Este proyecto utiliza Maven para la gestión de dependencias. Asegúrate de tener Maven instalado en tu sistema para compilar y ejecutar el proyecto.

- **Apache POI**: Para la lectura y escritura de archivos Excel.

## Configuración

1. **Clonar el Repositorio**:

   Si aún no tienes el repositorio en tu máquina local, clónalo con el siguiente comando:

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd java-duplicates-bp

2. **Compilar el proyecto**:

    ```bash
    mvn clean install

3. **Ejecutar la aplicacion**

    ```bash
    mvn exec:java
