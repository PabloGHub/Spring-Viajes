package launcher.springviajes.Cotroladores;

import launcher.springviajes.DTOs.*;
import launcher.springviajes.modelos.Actividad;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.modelos.Viaje;
import launcher.springviajes.repositorios.RepoActividad;
import launcher.springviajes.repositorios.RepoPerfil;
import launcher.springviajes.repositorios.RepoViaje;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class Empaquetador
{
    // Es esto o permitir bucle.
    // private ServiViaje _serviViaje;
    // private ServiPerfil _serviPerfil;

    // repito: es esto o permitir bucles.
    @Autowired
    private RepoViaje _repoViaje;
    @Autowired
    private RepoPerfil _repoPerfil;
    @Autowired
    private RepoActividad _repoActividad;


    // --- Empaquetadores --- //
    public DTOViajePuro empaquetarPuro(Viaje _viaje)
    {
        DTOViajePuro _NovoViaje = new DTOViajePuro();
        _NovoViaje.set_idViaje(_viaje.getIdViaje());
        _NovoViaje.set_nombre(_viaje.getNombre());
        _NovoViaje.set_descripcion(_viaje.getDescripcion());
        _NovoViaje.set_contraseña(_viaje.getPassword());
        return _NovoViaje;
    }
    // TODO: Refactorizar segun el "todo" de DTOViaje.
    public DTOViaje empaquetar(Viaje _viaje)
    {
        DTOViaje _NovoViaje = new DTOViaje();
        _NovoViaje.set_idViaje(_viaje.getIdViaje());
        _NovoViaje.set_nombre(_viaje.getNombre());
        _NovoViaje.set_descripcion(_viaje.getDescripcion());
        _NovoViaje.set_password(_viaje.getPassword());
        _repoPerfil.findAll().stream()
                .filter(perfil -> perfil.getViajes().contains(_viaje))
                .forEach(p -> _NovoViaje.get_participantes().add(empaquetarPuro(p)));
        
        return _NovoViaje;
    }



    public DTOPerfil empaquetar(Perfil _perfil)
    {
        DTOPerfil _NovoPerfil = new DTOPerfil();

        _NovoPerfil.set_idPerfil(_perfil.getIdUsuario());
        _NovoPerfil.set_nombre(_perfil.getNombre());
        _NovoPerfil.set_password(_perfil.getPassword());

        _perfil.getViajes().forEach(viaje -> _NovoPerfil.get_viajes().add(empaquetarPuro(viaje)));

        return _NovoPerfil;
    }
    public DTOPerfilPuro empaquetar(DTOPerfil _perfil)
    {
        DTOPerfilPuro _NovoPerfil = new DTOPerfilPuro();

        _NovoPerfil.set_idPerfil(_perfil.get_idPerfil());
        _NovoPerfil.set_nombre(_perfil.get_nombre());
        _NovoPerfil.set_password(_perfil.get_password());

        return _NovoPerfil;
    }
    public DTOPerfilPuro empaquetarPuro(Perfil _perfil)
    {
        DTOPerfilPuro _NovoPerfil = new DTOPerfilPuro();

        _NovoPerfil.set_idPerfil(_perfil.getIdUsuario());
        _NovoPerfil.set_nombre(_perfil.getNombre());
        _NovoPerfil.set_password(_perfil.getPassword());

        return _NovoPerfil;
    }


    // La fecha esta fatal, es 2024-11-06 cuando tendria que ser 06/11/2024.
    // TODO: Revisar y arreglar.
    public DTOActividad empaquetar(Actividad _actividad)
    {
        DTOActividad _NovoActividad = new DTOActividad();

        _NovoActividad.set_idActividad(_actividad.getIdActividad());
        _NovoActividad.set_titulo(_actividad.getTitulo());
        _NovoActividad.set_descripcion(_actividad.getDescripcion());
        _NovoActividad.set_fecha(_actividad.getFecha().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE));
        _NovoActividad.set_precio(_actividad.getPrecio());

        _NovoActividad.set_Viaje(empaquetarPuro(_actividad.getViaje()));

        return _NovoActividad;
    }





    // --- Desempaquetadores --- //
    public Viaje desempaquetar(DTOViajePuro _viaje)
    {
        Viaje _NovoViaje = (_viaje.get_idViaje() == null) ? new Viaje() :
                _repoViaje.findById(_viaje.get_idViaje()).orElse(null);

        if (_NovoViaje == null)
            return null;

        // Este forma permite crear nuevo si el id es inexsistente.
        // Viaje _NovoViaje = new Viaje();
        // _NovoViaje.setIdViaje((_viaje.get_idViaje()!=null) ? _viaje.get_idViaje() : null);

        _NovoViaje.setNombre
        (
            (_viaje.get_nombre() != null) ? _viaje.get_nombre() :
                    (_NovoViaje.getNombre() != null) ? _NovoViaje.getNombre() : "Desconocido"
        );
        _NovoViaje.setDescripcion
        (
                (_viaje.get_descripcion()) != null ? _viaje.get_descripcion() :
                        (_NovoViaje.getDescripcion() != null) ? _NovoViaje.getDescripcion() : "Desconocido"
        );
        _NovoViaje.setPassword
        (
                (_viaje.get_contraseña() != null) ? _viaje.get_contraseña() :
                        (_NovoViaje.getPassword() != null) ? _NovoViaje.getPassword() : "Desconocido"
        );

        return _NovoViaje;
    }

    public Perfil desempaquetar(DTOPerfil _perfil)
    {
        Perfil _NovoPerfil = (_perfil.get_idPerfil() == null) ? new Perfil() :
                _repoPerfil.findById(_perfil.get_idPerfil()).orElse(null);

        if (_NovoPerfil == null)
            return null;

        _NovoPerfil.setIdUsuario
        (
            (_perfil.get_idPerfil() != null) ? _perfil.get_idPerfil() :
                    (_NovoPerfil.getIdUsuario() != null) ? _NovoPerfil.getIdUsuario() : null
        );
        _NovoPerfil.setNombre
        (
            (_perfil.get_nombre() != null) ? _perfil.get_nombre() :
                    (_NovoPerfil.getNombre() != null) ? _NovoPerfil.getNombre() : "Desconocido"
        );
        _NovoPerfil.setPassword
        (
            (_perfil.get_password() != null) ? _perfil.get_password() :
                    (_NovoPerfil.getPassword() != null) ? _NovoPerfil.getPassword() : "Desconocido"
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

    // Son las 9, tengo mucho sueño y no se si esto esta bien.
    // TODO: Revisar.
    public Actividad desempaquetar(DTOActividad _actividad)
    {
        Actividad _NovoActividad = (_actividad.get_idActividad() == null) ? new Actividad() :
                _repoActividad.findById(_actividad.get_idActividad()).orElse(null);

        if (_NovoActividad == null)
            return null;

        _NovoActividad.setIdActividad
        (
            (_actividad.get_idActividad() != null) ? _actividad.get_idActividad() : null
        );
        _NovoActividad.setTitulo
        (
            (_actividad.get_titulo() != null) ? _actividad.get_titulo() : "Desconocido"
        );
        _NovoActividad.setDescripcion
        (
            (_actividad.get_descripcion() != null) ? _actividad.get_descripcion() : "Desconocido"
        );
        _NovoActividad.setFecha
        (
            (_actividad.get_fecha() != null) ? java.time.LocalDate.parse(_actividad.get_fecha()) : null
        );
        _NovoActividad.setPrecio
        (
            (_actividad.get_precio() != null) ? _actividad.get_precio() : null
        );
        _NovoActividad.setViaje
        (
            (_actividad.get_Viaje() != null) ? desempaquetar(_actividad.get_Viaje()) : null
        );

        return _NovoActividad;
    }
}
