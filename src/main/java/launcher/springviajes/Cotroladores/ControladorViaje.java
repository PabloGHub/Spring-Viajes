package launcher.springviajes.Cotroladores;


import launcher.springviajes.DTOs.*;
import launcher.springviajes.Servicios.ServiViaje;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaje")
@AllArgsConstructor
public class ControladorViaje
{
    private final ServiViaje serviViaje;

    // TODO: quitar la palabra id de todos.

    @GetMapping
    public List<DTOViajePuro> listarViajes(@RequestParam int _usu)
    {
        return serviViaje.listarViaje(_usu);
    }

    @PostMapping("/crear")
    public DTOViajePuro crearViaje(@RequestBody DTOViajePuro _viaje)
    {
        return serviViaje.guardar(_viaje);
    }

    @PostMapping("/participantes/nuevo")
    public DTOPerfil annadirParticipanteViaje(@RequestParam int _viaje, @RequestParam int _perfil)
    {
        return serviViaje.annadirParticipanteViaje(_viaje, _perfil);
    }

    @GetMapping("/participantes")
    public List<DTOPerfilPuro> verParticipantesViaje(@RequestParam int _viaje)
    {
        return serviViaje.verParticipantesViaje(_viaje);
    }

    @PostMapping("/participantes/eliminar")
    public DTOViaje eliminarParticipanteViaje(@RequestBody DTOEliminarParticipante _eliminarParticipante)
    {
        return serviViaje.eliminarParticipanteViaje(_eliminarParticipante.get_idViaje(), _eliminarParticipante.get_idPerfil());
    }


    @GetMapping("/actividad")
    public List<DTOActividad> verActividades(@RequestParam int _usu)
    {
        return serviViaje.verActividades(_usu);
    }


    @PostMapping("/actividad/nueva")
    public DTOActividad proponerActividad(@RequestBody DTOActividad _act)
    {
        return serviViaje.proponerActividad(_act);
    }



    @PostMapping("/actividad/votar")
    public DTOVotoPuro votarActividad(@RequestParam int _act, @RequestParam int _usu, @RequestParam int _voto)
    {
        return serviViaje.votarActividad(_act, _usu, _voto);
    }
}