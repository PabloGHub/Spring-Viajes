package launcher.springviajes.Cotroladores;


import launcher.springviajes.DTOs.DTOPerfil;
import launcher.springviajes.DTOs.DTOViaje;
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
}