package launcher.springviajes.Cotroladores;

import launcher.springviajes.DTOs.DTOActividad;
import launcher.springviajes.Servicios.ServiActividad;
import launcher.springviajes.modelos.Actividad;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import java.util.List;

@RestController
@RequestMapping("/actividad")
@AllArgsConstructor
public class ControladorActividad
{
    private final ServiActividad serviActividad;


    @GetMapping("/tos")
    public List<DTOActividad> obtenerActividades()
    {
        return serviActividad.darmeTodo();
    }
}
