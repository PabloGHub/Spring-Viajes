package launcher.springviajes;

// import lombok.allArgsConstructor;
import jakarta.transaction.Transactional;
import launcher.springviajes.DTOs.DTOViaje;
import launcher.springviajes.DTOs.DTOViajePuro;
import launcher.springviajes.Servicios.ServiViaje;
import launcher.springviajes.modelos.Viaje;
import launcher.springviajes.repositorios.RepoViaje;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
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

    void monatarBD_Pruebas()
    {

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

}
