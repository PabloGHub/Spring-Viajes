package launcher.springviajes.Cotroladores;

import launcher.springviajes.DTOs.DTOPerfil;
import launcher.springviajes.Servicios.ServiPerfil;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.List;


@RestController
@RequestMapping("/perfil")
@AllArgsConstructor
public class ControladorPerfil
{
    private final ServiPerfil serviPerfil;

    @GetMapping
    public List<DTOPerfil> listarPerfiles()
    {
        return serviPerfil.darmeTodo();
    }
}
