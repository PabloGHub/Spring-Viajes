package launcher.springviajes;

// import lombok.allArgsConstructor;
import launcher.springviajes.modelos.Viaje;
import launcher.springviajes.repositorios.RepoViaje;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class SpringViajesApplicationTests
{
    @Autowired
    private RepoViaje _rp;

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
}
