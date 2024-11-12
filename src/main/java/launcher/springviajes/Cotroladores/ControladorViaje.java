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
    public List<DTOViajePuro> listarViajes(@RequestParam int usuario)
    {
        return serviViaje.listarViaje(usuario);
    }

    @PostMapping("/crear")
    public DTOViajePuro crearViaje(@RequestBody DTOViajePuro _viaje)
    {
        return serviViaje.guardar(_viaje);
    }

    @PostMapping("/participantes/nuevo")
    public DTOPerfil annadirParticipanteViaje(@RequestParam int idViaje, @RequestParam int idPerfil)
    {
        return serviViaje.annadirParticipanteViaje(idViaje, idPerfil);
    }

    @GetMapping("/participantes")
    public List<DTOPerfilPuro> verParticipantesViaje(@RequestParam int idViaje)
    {
        return serviViaje.verParticipantesViaje(idViaje);
    }

    @PostMapping("/participantes/eliminar")
    public DTOViaje eliminarParticipanteViaje(@RequestBody DTOEliminarParticipante _eliminarParticipante)
    {
        return serviViaje.eliminarParticipanteViaje(_eliminarParticipante.get_idViaje(), _eliminarParticipante.get_idPerfil());
    }

    // TODO: cambiar a RequestParam
    @GetMapping("/actividad/{id}")
    public List<DTOActividad> verActividades(@PathVariable int id)
    {
        return serviViaje.verActividades(id);
    }


    @PostMapping("/actividad/nueva")
    public DTOActividad proponerActividad(@RequestBody DTOActividad _actividad)
    {
        return serviViaje.proponerActividad(_actividad);
    }



    @PostMapping("/actividad/votar")
    public DTOVotoPuro votarActividad(@RequestParam int _act, @RequestParam int _usu, @RequestParam int _voto)
    {
        return serviViaje.votarActividad(_act, _usu, _voto);
    }
}