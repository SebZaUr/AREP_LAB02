
import arep.lab2.LambdaServer;
import arep.lab2.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AppTest {
    private static Map<String, Service> getRoutes;
    private static Map<String, Service> postRoutes;
    private static String filesPath;

    @BeforeEach
    public void setUp() {
        getRoutes = new HashMap<>();
        postRoutes = new HashMap<>();
        filesPath = "";
    }

    @Test
    public void testPost_AddsRouteAndService() {
        // Crear un mock de la clase Service
        Service serviceMock = mock(Service.class);
        String url = "/testPost";

        // Llamar al método estático post
        LambdaServer.post(url, serviceMock);

        // Verificar que la ruta y el servicio se han añadido al mapa postRoutes
        assertTrue(postRoutes.containsKey(url), "La URL debería estar en el mapa de rutas POST.");
        assertEquals(serviceMock, postRoutes.get(url), "El servicio asociado debería ser el mismo que el pasado.");
    }


    @Test
    public void testServerStaticFile_FileNotFound() {
        LambdaServer.staticfiles("/invalid_path");

        String result = LambdaServer.serverStaticFile("/nonexistent.txt");

        assertEquals("File not found: /nonexistent.txt", result, "El mensaje de error debería indicar que el archivo no se encuentra.");
    }

    @Test
    public void testFindHandler_GetMethod() {
        Service serviceMock = mock(Service.class);
        String url = "/testGet";
        getRoutes.put(url, serviceMock);

        Service result = LambdaServer.findHandler("GET", url);

        assertEquals(serviceMock, result, "El handler devuelto debería ser el asociado a la ruta GET.");
    }

    @Test
    public void testFindHandler_PostMethod() {
        Service serviceMock = mock(Service.class);
        String url = "/testPost";
        postRoutes.put(url, serviceMock);

        Service result = LambdaServer.findHandler("POST", url);

        assertEquals(serviceMock, result, "El handler devuelto debería ser el asociado a la ruta POST.");
    }

    @Test
    public void testFindHandler_InvalidMethod() {
        Service result = LambdaServer.findHandler("PUT", "/testPut");

        assertNull(result, "El resultado debería ser null para métodos HTTP no soportados.");
    }
}

