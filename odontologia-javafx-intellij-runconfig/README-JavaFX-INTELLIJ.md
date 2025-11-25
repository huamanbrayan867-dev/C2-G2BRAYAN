# Proyecto preparado para IntelliJ + JDK 17 (Windows)

Se ha preparado este ZIP con cambios mínimos para ejecutar el proyecto con **Java 17** y **JavaFX** usando **Maven**.

## Qué se hizo
- Se encontró un pom.xml existente; no se sobrescribió.
- Si usas Gradle, dímelo y puedo crear un `build.gradle` en su lugar.
- Añadí instrucciones para ejecutar desde IntelliJ y con Maven en línea de comandos.

## Instrucciones (IntelliJ)
1. Importa el proyecto como **Maven** (File > Open > seleccionar la carpeta del proyecto con `pom.xml`).
2. Espera a que Maven descargue dependencias (org.openjfx, sqlite-jdbc).
3. Edita la configuración de ejecución (Run > Edit Configurations...):
   - Si usas el plugin `javafx-maven-plugin`, puedes ejecutar con la goal `javafx:run` o usar la clase `Main` con VM options:
     ``--module-path "%JAVA_HOME%\javafx-sdk-XX\lib" --add-modules javafx.controls,javafx.fxml``
   - Si usas Maven: `mvn javafx:run -Dexec.mainClass="tu.paquete.MainApp"`

## Notas
- Reemplaza `your.package.MainApp` en `pom.xml` por la clase `main` real (por ejemplo `com.miempresa.MainApp`). Si me indicas la clase Main puedo reemplazarlo automáticamente.
- Si ya tienes Maven en IntelliJ, el IDE creará las configuraciones automáticamente.