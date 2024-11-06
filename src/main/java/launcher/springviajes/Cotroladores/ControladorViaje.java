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


    @GetMapping
    public List<DTOViaje> listarViajes()
    {
        return serviViaje.darmeTodo();
    }

    @PostMapping("/crear")
    public DTOViaje crearViaje(@RequestBody DTOViaje _viaje)
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
    public DTOViajeDatos eliminarParticipanteViaje(@RequestBody DTOEliminarParticipante _eliminarParticipante)
    {
        return serviViaje.eliminarParticipanteViaje(_eliminarParticipante.get_idViaje(), _eliminarParticipante.get_idPerfil());
    }

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
}