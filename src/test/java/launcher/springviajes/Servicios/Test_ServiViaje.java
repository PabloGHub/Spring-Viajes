package launcher.springviajes.Servicios;

import jakarta.transaction.Transactional;
import launcher.springviajes.DTOs.*;
import launcher.springviajes.modelos.Viaje;
import launcher.springviajes.repositorios.RepoViaje;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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
        System.out.println("Preparacion");
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


        System.out.println("Ejecucion");
        // --- Ejecucion --- //
        DTOPerfil _p2 = _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil());

        DTOViaje _v3 = _sv.eliminarParticipanteViaje(_v2.get_idViaje(), _p2.get_idPerfil());


        System.out.println("Resolucion");
        // --- Resolucion --- //
        assertNotNull(_v3);
    }

    @Test
    @Tag("viaje")
    void test_eliminarParticipanteViaje_fallido()
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
        //DTOPerfil _p2 = _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil());

        Exception _ex = assertThrows(Exception.class, () -> _sv.eliminarParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil()));

        // --- Resolucion --- //
        assertTrue(_ex.getMessage().contains("Error:"));
    }


    // --- listarViajes
    @Test
    @Tag("viaje")
    void test_listarViajes_correcto()
    {
        // --- Preparacion --- //
        DTOPerfil _p1 = new DTOPerfil();
        _p1.set_nombre("Pedro");
        _p1.set_password("1234");
        DTOPerfil _p1_2 = _sp.guardar(_p1);

        DTOViajePuro _v = new DTOViajePuro();
        _v.set_nombre       ("Viaje a la luna");
        _v.set_descripcion  ("Viaje a la luna con todo incluido");
        _v.set_contraseña   ("1234");
        DTOViajePuro _v2 = _sv.guardar(_v);

        _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil());

        // --- Ejecucion --- //
        List<DTOViajePuro> _viajes = _sv.listarViaje(_p1_2.get_idPerfil());

        // --- Resolucion --- //
        assertNotNull(_viajes);
    }

    @Test
    @Tag("viaje")
    void test_listarViajes_fallido()
    {
        // --- Preparacion --- //
        // --- Ejecucion --- //
        Exception _ex = assertThrows(Exception.class, () -> _sv.listarViaje(null));

        // --- Resolucion --- //
        assertTrue(_ex.getMessage().contains("Error:"));
    }

    // --- proponerActividad
    @Test
    @Tag("viaje")
    void test_proponerActividad_correcto()
    {
        // --- Preparacion --- //
        DTOPerfil _p1 = new DTOPerfil();
        _p1.set_nombre("Pedro");
        _p1.set_password("1234");
        DTOPerfil _p1_2 = _sp.guardar(_p1);

        DTOViajePuro _v = new DTOViajePuro();
        _v.set_nombre       ("Viaje a la luna");
        _v.set_descripcion  ("Viaje a la luna con todo incluido");
        _v.set_contraseña   ("1234");
        DTOViajePuro _v2 = _sv.guardar(_v);

        _sv.annadirParticipanteViaje(_v2.get_idViaje(), _p1_2.get_idPerfil());

        // --- Ejecucion --- //
        DTOActividad _act = new DTOActividad();
        _act.set_titulo("Actividad");
        _act.set_descripcion("Actividad de prueba");
        _act.set_Viaje(_v2);
        _act.set_fecha(new DTOFecha(10, 10, 2024));
        _act.set_precio(10f);
        DTOActividad _act2 = _sv.proponerActividad(_act, _p1_2.get_idPerfil());

        // --- Resolucion --- //
        assertNotNull(_act2);
    }

    @Test
    @Tag("viaje")
    void test_proponerActividad_fallido()
    {
        // --- Preparacion --- //
        // --- Ejecucion --- //
        Exception _ex = assertThrows(Exception.class, () -> _sv.proponerActividad(null, 0));

        // --- Resolucion --- //
        assertTrue(_ex.getMessage().contains("Error:"));
    }

}
