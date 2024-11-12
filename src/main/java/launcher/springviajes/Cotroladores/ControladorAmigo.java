package launcher.springviajes.Cotroladores;


import launcher.springviajes.DTOs.DTOPerfil;
import launcher.springviajes.DTOs.DTOPerfilPuro;
import launcher.springviajes.Servicios.ServiPerfil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/amigos")
public class ControladorAmigo
{
    private final ServiPerfil serviPerfil;

    @GetMapping
    public List<DTOPerfilPuro> listarAmigos(@RequestParam int _perfil)
    {
        return serviPerfil.listarAmigos(_perfil);
    }
}
