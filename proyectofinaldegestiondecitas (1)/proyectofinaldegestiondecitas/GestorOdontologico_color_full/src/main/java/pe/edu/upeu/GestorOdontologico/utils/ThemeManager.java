package pe.edu.upeu.GestorOdontologico.utils;

import javafx.scene.Scene;

/**
 * Simple theme manager para gestionar y aplicar hojas de estilo CSS en la aplicación JavaFX.
 * Mantiene la ruta relativa a recursos (p. ej. "/css/estilo-nuevo.css") y aplica el CSS a una Scene.
 */
public class ThemeManager {

    private static String currentTheme = "/css/estilo-nuevo.css";

    public static String getCurrentTheme() {
        return currentTheme;
    }

    /**
     * Establece la ruta al tema (ruta de recurso, por ejemplo: "/css/estilo-azul.css").
     */
    public static void setTheme(String themeResourcePath) {
        if (themeResourcePath != null && !themeResourcePath.isEmpty()) {
            currentTheme = themeResourcePath;
        }
    }

    /**
     * Aplica el tema actual a la escena: remueve hojas de estilos previas y añade la actual.
     */
    public static void applyTheme(Scene scene) {
        if (scene == null) return;

        // Eliminar estilos anteriores ubicados en /css/
        scene.getStylesheets().removeIf(s -> s.contains("/css/"));

        try {
            String css = ThemeManager.class.getResource(currentTheme).toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception e) {
            // En caso no encuentre el recurso, intentar añadir la ruta tal cual (por si es toExternalForm ya)
            if (!scene.getStylesheets().contains(currentTheme)) {
                scene.getStylesheets().add(currentTheme);
            }
        }
    }
}
