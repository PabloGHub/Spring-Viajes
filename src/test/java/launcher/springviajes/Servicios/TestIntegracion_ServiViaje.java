package launcher.springviajes.Servicios;

import launcher.springviajes.modelos.Viaje;
import launcher.springviajes.repositorios.RepoViaje;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestIntegracion_ServiViaje
{
    @Mock
    private RepoViaje _repoViaje;

    @Test
    @Tag("viaje")
    void test_crearViaje_Integracion()
    {
        // --- Preparacion --- //
        Viaje _v = new Viaje();
        _v.setNombre("MeQuieroMorir");
        _v.setPassword("123");
        _v.setDescripcion("QuieroMorirme");

        when(_repoViaje.save(any(Viaje.class))).thenReturn(_v);

        // --- Ejecucion --- //
        Viaje _v2 = _repoViaje.save(new Viaje());

        // --- Resolucion --- //
        assertNotNull(_v2);
        assertEquals(_v.getNombre(), _v2.getNombre());
    }
}
