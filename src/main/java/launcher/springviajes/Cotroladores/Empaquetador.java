package launcher.springviajes.Cotroladores;

import launcher.springviajes.DTOs.DTOPerfil;
import launcher.springviajes.DTOs.DTOViaje;
import launcher.springviajes.Servicios.ServiPerfil;
import launcher.springviajes.Servicios.ServiViaje;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.modelos.Viaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Empaquetador
{
    @Autowired
    private ServiViaje _serviViaje;
    @Autowired
    private ServiPerfil _serviPerfil;


    // --- Empaquetadores --- //
    public DTOViaje empaquetar(Viaje _viaje)
    {
        DTOViaje _NovoViaje = new DTOViaje();
        _NovoViaje.set_idViaje(_viaje.getIdViaje());
        _NovoViaje.set_nombre(_viaje.getNombre());
        _NovoViaje.set_descripcion(_viaje.getDescripcion());
        _NovoViaje.set_contraseña(_viaje.getPassword());
        return _NovoViaje;
    }

    public DTOPerfil empaquetar(Perfil _perfil)
    {
        DTOPerfil _NovoPerfil = new DTOPerfil();

        _NovoPerfil.set_idPerfil(_perfil.getIdUsuario());
        _NovoPerfil.set_nombre(_perfil.getNombre());
        _NovoPerfil.set_password(_perfil.getPassword());

        _perfil.getViajes().forEach(viaje -> _NovoPerfil.get_viajes().add(empaquetar(viaje)));

        return _NovoPerfil;
    }


    // --- Desempaquetadores --- //
    public Viaje desempaquetar(DTOViaje _viaje)
    {
        Viaje _NovoViaje = (_viaje.get_idViaje() == null) ? new Viaje() :
                _serviViaje.darmeUnoViaje(_viaje.get_idViaje());

        // Este forma permite crear nuevo si el id es inexsistente.
        // Viaje _NovoViaje = new Viaje();
        // _NovoViaje.setIdViaje((_viaje.get_idViaje()!=null) ? _viaje.get_idViaje() : null);

        _NovoViaje.setNombre
        (
            (_viaje.get_nombre() != null) ? _viaje.get_nombre() :
                    (_NovoViaje.getNombre() != null) ? _NovoViaje.getNombre() : null
        );
        _NovoViaje.setDescripcion
        (
                (_viaje.get_descripcion()) != null ? _viaje.get_descripcion() :
                        (_NovoViaje.getDescripcion() != null) ? _NovoViaje.getDescripcion() : null
        );
        _NovoViaje.setPassword
        (
                (_viaje.get_contraseña() != null) ? _viaje.get_contraseña() :
                        (_NovoViaje.getPassword() != null) ? _NovoViaje.getPassword() : null
        );

        return _NovoViaje;
    }

    public Perfil desempaquetar(DTOPerfil _perfil)
    {
        Perfil _NovoPerfil = (_perfil.get_idPerfil() == null) ? new Perfil() :
                _serviPerfil.darmeUnoPerfil(_perfil.get_idPerfil());

        _NovoPerfil.setIdUsuario
        (
            (_perfil.get_idPerfil() != null) ? _perfil.get_idPerfil() :
                    (_NovoPerfil.getIdUsuario() != null) ? _NovoPerfil.getIdUsuario() : null
        );
        _NovoPerfil.setNombre
        (
            (_perfil.get_nombre() != null) ? _perfil.get_nombre() :
                    (_NovoPerfil.getNombre() != null) ? _NovoPerfil.getNombre() : null
        );
        _NovoPerfil.setPassword
        (
            (_perfil.get_password() != null) ? _perfil.get_password() :
                    (_NovoPerfil.getPassword() != null) ? _NovoPerfil.getPassword() : null
        );
        _NovoPerfil.setViajes
        (
            (_perfil.get_viajes() != null) ? _perfil.get_viajes()
                    .stream()
                    .map(this::desempaquetar)
                    .collect(Collectors.toSet()) :
                    (_NovoPerfil.getViajes() != null) ? _NovoPerfil.getViajes() : null
        );

        return _NovoPerfil;
    }
}
