package launcher.springviajes.Cotroladores;

import launcher.springviajes.DTOs.DTOPerfil;
import launcher.springviajes.DTOs.DTOPerfilPuro;
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

    @GetMapping("/darmeUno")
    public DTOPerfil darmeUno(@RequestParam int _perfil)
    {
        return serviPerfil.darmeUno(_perfil);
    }

    @PostMapping("/encuentra")
    public DTOPerfilPuro encuentraUno(@RequestBody DTOPerfilPuro _perfil)
    {
        return serviPerfil.encuentraUno(_perfil);
    }
}
