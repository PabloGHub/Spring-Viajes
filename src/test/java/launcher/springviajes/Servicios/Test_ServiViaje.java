package launcher.springviajes.Servicios;

import jakarta.transaction.Transactional;
import launcher.springviajes.DTOs.DTOViajePuro;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class Test_ServiViaje
{
    @Autowired
    private ServiViaje _sv;

    @Test
    @Tag("viaje")
    void test_CrearViaje_correcto()
    {
        // --- Preparacion --- //
        DTOViajePuro _v = new DTOViajePuro();
        _v.set_nombre       ("Viaje a la luna");
        _v.set_descripcion  ("Viaje a la luna con todo incluido");
        _v.set_contraseña   ("1234");


        // --- Ejecucion --- //
        DTOViajePuro _v2 = _sv.guardar(_v);


        // --- Resolucion --- //
        assertNotNull(_v2);
        assertEquals(_v.get_nombre(), _v2.get_nombre());
    }

    @Test
    @Tag("viaje")
    void test_CrearViaje_fallido()
    {
        // --- Preparacion --- //
        DTOViajePuro _v = new DTOViajePuro();
        _v.set_nombre(null);
        _v.set_descripcion(null);
        _v.set_contraseña("");


        // --- Ejecucion --- //
        // --- Resolucion --- //
        Exception _ex = assertThrows(Exception.class, () -> _sv.guardar(_v));

        assertTrue(_ex.getMessage().contains("Error al guardar el viaje"));
    }
}
