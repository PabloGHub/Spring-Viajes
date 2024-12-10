package launcher.springviajes.Servicios;

import jakarta.transaction.Transactional;
import launcher.springviajes.DTOs.DTOPerfil;
import launcher.springviajes.DTOs.DTOPerfilPuro;
import launcher.springviajes.DTOs.DTOViajePuro;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
public class Test_ServiViaje
{
    @Autowired
    private ServiViaje _sv;
    @Autowired
    private ServiPerfil _sp;

    // --- CrearViaje
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

        assertTrue(_ex.getMessage().contains("Error:"));
    }


    // --- annadirParticipanteViaje
    @Test
    @Tag("viaje")
    void test_annadirParticipante_correcto() // Mayia Potyia
    {
        // --- Preparacion --- //
        DTOViajePuro _v = new DTOViajePuro();
        _v.set_nombre       ("Viaje a la luna");
        _v.set_descripcion  ("Viaje a la luna con todo incluido");
        _v.set_contraseña   ("1234");
        DTOViajePuro _v2 = _sv.guardar(_v);

        DTOPerfil _p1 = new DTOPerfil();
        _p1.set_nombre("Pedro");
        _p1.set_password("1234");
        DTOPerfil _p1_2 = _sp.guardar(_p1);


        // --- Ejecucion --- //
        DTOPerfil _p2 = _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil());


        // --- Resolucion --- //
        assertNotNull(_p2);
    }

    @Test
    @Tag("viaje")
    void test_annadirParticipante_fallido()
    {
        // --- Preparacion --- //
        DTOViajePuro _v = new DTOViajePuro();
        _v.set_nombre       ("Viaje a la luna");
        _v.set_descripcion  ("Viaje a la luna con todo incluido");
        _v.set_contraseña   ("1234");
        DTOViajePuro _v2 = _sv.guardar(_v);

        DTOPerfil _p1 = new DTOPerfil();
        _p1.set_nombre("Pedro");
        _p1.set_password("1234");
        DTOPerfil _p1_2 = _sp.guardar(_p1);


        // --- Ejecucion --- //
        _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil());
        Exception _ex = assertThrows(Exception.class, () -> _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil()));

        // --- Resolucion --- //
        assertNotNull(_ex);

    }


    // --- verParticipantesViaje
    @Test
    @Tag("viaje")
    void test_verParticipantesViaje_correcto()
    {
        // --- Preparacion --- //
        DTOViajePuro _v = new DTOViajePuro();
        _v.set_nombre       ("Viaje a la luna");
        _v.set_descripcion  ("Viaje a la luna con todo incluido");
        _v.set_contraseña   ("1234");
        DTOViajePuro _v2 = _sv.guardar(_v);

        DTOPerfil _p1 = new DTOPerfil();
        _p1.set_nombre("Pedro");
        _p1.set_password("1234");
        DTOPerfil _p1_2 = _sp.guardar(_p1);
        _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil());

        DTOPerfil _p2 = new DTOPerfil();
        _p2.set_nombre("Juan");
        _p2.set_password("1234");
        DTOPerfil _p2_2 = _sp.guardar(_p2);
        _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p2_2.get_idPerfil());

        // --- Ejecucion --- //
        List<DTOPerfilPuro> _participantes = _sv.verParticipantesViaje(_v2.get_idViaje());

        // --- Resolucion --- //
        assertNotNull(_participantes);
    }

    @Test
    @Tag("viaje")
    void test_verParticipantesViaje_fallido()
    {
        // --- Preparacion --- //
        // --- Ejecucion --- //
        Exception _ex = assertThrows(Exception.class, () -> _sv.verParticipantesViaje(0));

        // --- Resolucion --- //
        assertTrue(_ex.getMessage().contains("Error:"));
    }


    // --- eliminarParticipanteViaje
    @Test
    @Tag("viaje")
    void test_eliminarParticipanteViaje_correcto()
    {
        // --- Preparacion --- //
        // --- Ejecucion --- //
        Exception _ex = assertThrows(Exception.class, () -> _sv.verParticipantesViaje(0));

        // --- Resolucion --- //
        assertTrue(_ex.getMessage().contains("Error:"));
    }
}
