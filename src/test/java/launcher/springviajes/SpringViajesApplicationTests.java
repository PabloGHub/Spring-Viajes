package launcher.springviajes;

// import lombok.allArgsConstructor;
import launcher.springviajes.DTOs.DTOViaje;
import launcher.springviajes.DTOs.DTOViajePuro;
import launcher.springviajes.Servicios.ServiViaje;
import launcher.springviajes.modelos.Viaje;
import launcher.springviajes.repositorios.RepoViaje;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringViajesApplicationTests
{
    @Autowired
    private ServiViaje _sv;

    @Test
    void contextLoads()
    {
        // List<Viaje> _viajes = _rp.findAll();
        // for (Viaje _v : _viajes)
        // {
        //     System.out.println(_v.getIdViaje());
        //     System.out.println(_v.getNombre());
        //     System.out.println(_v.getDescripcion());
        // }


        // Cosas mias raras
        List<String> nombres = Arrays.asList("Juan", "Pedro", "Maria", "Ana");

        List<String> resultado = nombres.stream()
            .map(nombre -> {
                if (nombre.startsWith("J")) {
                    return nombre.toUpperCase();
                } else {
                    return nombre.toLowerCase();
                }
            })
            .toList();

        resultado.forEach(System.out::println);
    }

    @Test
    @Tag("Plantilla")
    @Disabled // Retirar antes de volar.
    void test_plantilla()
    {
        // --- Preparacion --- //

        // --- Ejecucion --- //

        // --- Resolucion --- //
    }


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

        if (_ex.getMessage().contains("Nombre no puede ser nulo"))
            assertTrue(true);
        else if (_ex.getMessage().contains("Descripcion no puede ser nulo"))
            assertTrue(true);
        else if (_ex.getMessage().contains("Password no puede ser nulo"))
            assertTrue(true);
    }
}
