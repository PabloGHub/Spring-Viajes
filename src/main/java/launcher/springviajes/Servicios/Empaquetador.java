package launcher.springviajes.Servicios;

import launcher.springviajes.DTOs.*;
import launcher.springviajes.modelos.Actividad;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.modelos.Viaje;
import launcher.springviajes.modelos.Voto;
import launcher.springviajes.repositorios.RepoActividad;
import launcher.springviajes.repositorios.RepoPerfil;
import launcher.springviajes.repositorios.RepoViaje;
import launcher.springviajes.repositorios.RepoVoto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
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
    @Autowired
    private RepoVoto _repoVoto;


    // ---------------------------------------------------- Empaquetadores --- //
    // ------ Viaje ------ //
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
    public DTOViajePuro empaquetar(DTOViaje _viaje)
    {
        DTOViajePuro _NovoViaje = new DTOViajePuro();

        _NovoViaje.set_idViaje(_viaje.get_idViaje());
        _NovoViaje.set_nombre(_viaje.get_nombre());
        _NovoViaje.set_descripcion(_viaje.get_descripcion());
        _NovoViaje.set_contraseña(_viaje.get_password());

        return _NovoViaje;
    }
    public DTOViajePuro empaquetarPuro(Viaje _viaje)
    {
        DTOViajePuro _NovoViaje = new DTOViajePuro();

        _NovoViaje.set_idViaje(_viaje.getIdViaje());
        _NovoViaje.set_nombre(_viaje.getNombre());
        _NovoViaje.set_descripcion(_viaje.getDescripcion());
        _NovoViaje.set_contraseña(_viaje.getPassword());

        return _NovoViaje;
    }



    // ------ Perfil ------ //
    public DTOPerfil empaquetar(Perfil _perfil)
    {
        DTOPerfil _NovoPerfil = new DTOPerfil();

        _NovoPerfil.set_idPerfil(_perfil.getIdUsuario());
        _NovoPerfil.set_nombre(_perfil.getNombre());
        _NovoPerfil.set_password(_perfil.getPassword());

        _perfil.getViajes().forEach(viaje -> _NovoPerfil.get_viajes().add(empaquetarPuro(viaje)));
        _perfil.getAmigos().forEach(amigo -> _NovoPerfil.get_amigos().add(empaquetarPuro(amigo)));

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




    // ------ Fecha ------ //
    public DTOFecha empaquetar(LocalDate _fecha)
    {
        DTOFecha _NovoFecha = new DTOFecha();

        _NovoFecha.set_dia(_fecha.getDayOfMonth());
        _NovoFecha.set_mes(_fecha.getMonthValue());
        _NovoFecha.set_anno(_fecha.getYear());

        return _NovoFecha;
    }




    // ------ Actividad ------ //
    public DTOActividad empaquetar(Actividad _actividad)
    {
        DTOActividad _NovoActividad = new DTOActividad();

        _NovoActividad.set_idActividad(_actividad.getIdActividad());
        _NovoActividad.set_titulo(_actividad.getTitulo());
        _NovoActividad.set_descripcion(_actividad.getDescripcion());
        _NovoActividad.set_fecha(empaquetar(_actividad.getFecha()));
        _NovoActividad.set_precio(_actividad.getPrecio());

        _NovoActividad.set_Viaje(empaquetarPuro(_actividad.getViaje()));

        _repoVoto.findByActividad(_actividad)
                .forEach(v -> _NovoActividad.get_Votos().add(empaquetarPuro(v)));

        return _NovoActividad;
    }
    public DTOActividadPuro empaquetar(DTOActividad _actividad)
    {
        DTOActividadPuro _NovoActividad = new DTOActividadPuro();

        _NovoActividad.set_idActividad(_actividad.get_idActividad());
        _NovoActividad.set_titulo(_actividad.get_titulo());
        _NovoActividad.set_descripcion(_actividad.get_descripcion());
        _NovoActividad.set_fecha(_actividad.get_fecha());
        _NovoActividad.set_precio(_actividad.get_precio());

        return _NovoActividad;
    }
    public DTOActividadPuro empaquetarPuro(Actividad _actividad)
    {
        DTOActividadPuro _NovoActividad = new DTOActividadPuro();

        _NovoActividad.set_idActividad(_actividad.getIdActividad());
        _NovoActividad.set_titulo(_actividad.getTitulo());
        _NovoActividad.set_descripcion(_actividad.getDescripcion());
        _NovoActividad.set_fecha(empaquetar(_actividad.getFecha()));
        _NovoActividad.set_precio(_actividad.getPrecio());

        return _NovoActividad;
    }




    // ------ Voto ------ //
    public DTOVoto empaquetar(Voto _voto)
    {
        DTOVoto _novoVoto = new DTOVoto();

        _novoVoto.set_idVoto(_voto.getIdVoto());
        _novoVoto.set_perfil(empaquetarPuro(_voto.getPerfil()));
        //_novoVoto.set_viaje(empaquetarPuro(_voto.getActividad()));
        _novoVoto.set_voto(_voto.getVoto());

        return _novoVoto;
    }
    public DTOVotoPuro empaquetar(DTOVoto _voto)
    {
        DTOVotoPuro _novoVoto = new DTOVotoPuro();

        _novoVoto.set_idVoto(_voto.get_idVoto());
        _novoVoto.set_voto(_voto.get_voto());

        return _novoVoto;
    }
    public DTOVotoPuro empaquetarPuro(Voto _voto)
    {
        DTOVotoPuro _novoVoto = new DTOVotoPuro();

        _novoVoto.set_idVoto(_voto.getIdVoto());
        _novoVoto.set_voto(_voto.getVoto());

        return _novoVoto;
    }





    // ---------------------------------------------------- Comprobar --- //
    Boolean compo(Object _dato)
    {
        return switch (_dato)
        {
            case Integer i -> i >= 0;
            case Double v -> v >= 0;
            case Float v -> v >= 0;
            case String s -> !s.isEmpty() && !s.contains("<") && !s.contains(">");
            case null, default -> _dato != null;
        };
    }




    // ---------------------------------------------------- Desempaquetadores --- //
    public Viaje desempaquetar(DTOViajePuro _viaje)
    {
        Viaje _NovoViaje = (_viaje.get_idViaje() == null) ? new Viaje() :
                _repoViaje.findById(_viaje.get_idViaje()).orElse(null);

        if (_NovoViaje == null)
            return null;

        // Este forma permite crear nuevo si el id es inexistente.
        // Viaje _NovoViaje = new Viaje();
        // _NovoViaje.setIdViaje((_viaje.get_idViaje()!=null) ? _viaje.get_idViaje() : null);

        _NovoViaje.setNombre
        (
            (_viaje.get_nombre() != null) ? _viaje.get_nombre() :
                    (_NovoViaje.getNombre() != null) ? _NovoViaje.getNombre() : null
        );
        if (!compo(_NovoViaje.getNombre())) throw new NullPointerException("Error: Nombre -> Viaje.");
        _NovoViaje.setDescripcion
        (
                (_viaje.get_descripcion()) != null ? _viaje.get_descripcion() :
                        (_NovoViaje.getDescripcion() != null) ? _NovoViaje.getDescripcion() : null
        );
        if (!compo(_NovoViaje.getDescripcion())) throw new NullPointerException("Error: Descripcion -> Viaje.");
        _NovoViaje.setPassword
        (
                (_viaje.get_contraseña() != null) ? _viaje.get_contraseña() :
                        (_NovoViaje.getPassword() != null) ? _NovoViaje.getPassword() : null
        );
        if (!compo(_NovoViaje.getPassword())) throw new NullPointerException("Error: Contraseña -> Viaje.");

        return _NovoViaje;
    }

    public Perfil desempaquetar(DTOPerfil _perfil)
    {
        Perfil _NovoPerfil = (_perfil.get_idPerfil() == null) ? new Perfil() :
                _repoPerfil.findById(_perfil.get_idPerfil()).orElse(null);

        if (_NovoPerfil == null)
            return null;

        // _NovoPerfil.setIdUsuario
        // (
        //     (_perfil.get_idPerfil() != null) ? _perfil.get_idPerfil() :
        //             (_NovoPerfil.getIdUsuario() != null) ? _NovoPerfil.getIdUsuario() : null
        // );
        _NovoPerfil.setNombre
        (
            (_perfil.get_nombre() != null) ? _perfil.get_nombre() :
                    (_NovoPerfil.getNombre() != null) ? _NovoPerfil.getNombre() : null
        );
        if (!compo(_NovoPerfil.getNombre())) throw new NullPointerException("Error: Nombre -> Perfil.");
        _NovoPerfil.setPassword
        (
            (_perfil.get_password() != null) ? _perfil.get_password() :
                    (_NovoPerfil.getPassword() != null) ? _NovoPerfil.getPassword() : null
        );
        if (!compo(_NovoPerfil.getPassword())) throw new NullPointerException("Error: Contraseña -> Perfil.");
        _NovoPerfil.setViajes
        (
            (_perfil.get_viajes() != null) ? _perfil.get_viajes()
                    .stream()
                    .map(this::desempaquetar)
                    .collect(Collectors.toSet()) :
                    (_NovoPerfil.getViajes() != null) ? _NovoPerfil.getViajes() : null
        );
        if (!compo(_NovoPerfil.getViajes())) throw new NullPointerException("Error: Viajes -> Perfil.");
        _NovoPerfil.setAmigos
        (
            (_perfil.get_amigos() != null) ? _perfil.get_amigos()
                    .stream()
                    .map(amigo -> _repoPerfil.findById(amigo.get_idPerfil()).orElse(null))
                    .collect(Collectors.toSet()) :
                    (_NovoPerfil.getAmigos() != null) ? _NovoPerfil.getAmigos() : null
        );
        if (!compo(_NovoPerfil.getAmigos())) throw new NullPointerException("Error: Amigos -> Perfil.");

        return _NovoPerfil;
    }


    public LocalDate desempaquetar(DTOFecha _fecha)
    {
        return LocalDate.of(_fecha.get_anno(), _fecha.get_mes(), _fecha.get_dia());
    }





    // Son las 9, tengo mucho sueño y no sé si esto está bien.
    public Actividad desempaquetar(DTOActividad _actividad)
    {
        Actividad _NovoActividad = (_actividad.get_idActividad() == null) ? new Actividad() :
                _repoActividad.findById(_actividad.get_idActividad()).orElse(null);

        if (_NovoActividad == null)
            return null;

        _NovoActividad.setTitulo
        (
            (_actividad.get_titulo() != null) ? _actividad.get_titulo() :
                    (_NovoActividad.getTitulo() != null) ? _NovoActividad.getTitulo() : null
        );
        if (!compo(_NovoActividad.getTitulo())) throw new NullPointerException("Error: Titulo -> Actividad.");

        _NovoActividad.setDescripcion
        (
            (_actividad.get_descripcion() != null) ? _actividad.get_descripcion() :
                    (_NovoActividad.getDescripcion() != null) ? _NovoActividad.getDescripcion() : null
        );
        if (!compo(_NovoActividad.getDescripcion())) throw new NullPointerException("Error: Descripcion -> Actividad.");

        _NovoActividad.setFecha
        (
            (_actividad.get_fecha() != null) ? desempaquetar(_actividad.get_fecha()) :
                    (_NovoActividad.getFecha() != null) ? _NovoActividad.getFecha() : null
        );
        if (!compo(_NovoActividad.getFecha())) throw new NullPointerException("Error: Fecha -> Actividad.");

        _NovoActividad.setPrecio
        (
            (_actividad.get_precio() != null) ? _actividad.get_precio() :
                    (_NovoActividad.getPrecio() != null) ? _NovoActividad.getPrecio() : null
        );
        if (!compo(_NovoActividad.getPrecio())) throw new NullPointerException("Error: Precio -> Actividad.");

        _NovoActividad.setViaje
        (
            (_actividad.get_Viaje() != null) ? desempaquetar(_actividad.get_Viaje()) :
                    (_NovoActividad.getViaje() != null) ? _NovoActividad.getViaje() : null
        );
        if (!compo(_NovoActividad.getViaje())) throw new NullPointerException("Error: Viaje -> Actividad.");


        return _NovoActividad;
    }



    public Voto desempaquetar(DTOVoto _voto)
    {
        Voto _novoVoto = (_voto.get_idVoto() == null) ? new Voto() :
                (
                        _repoPerfil.findById(_voto.get_idVoto()).orElse(null) == null ||
                        _repoActividad.findById(_voto.get_idVoto()).orElse(null) == null
                ) ? null :
                        _repoVoto.findById(_voto.get_idVoto()).orElse(null);

        if (_novoVoto == null)
            throw new NullPointerException("Voto no puede ser nulo(Nunca deberia salta esto)");

        _novoVoto.setPerfil
        (
            (_voto.get_perfil() != null) ?
                (_repoPerfil.findById(_voto.get_perfil().get_idPerfil()).orElse(null) != null) ?
                    _repoPerfil.findById(_voto.get_perfil().get_idPerfil()).orElse(null) : null
                        : (_novoVoto.getPerfil() != null) ? _novoVoto.getPerfil() : null
        );
        if (!compo(_novoVoto.getPerfil())) throw new NullPointerException("Error: Perfil -> Voto.");
        _novoVoto.setActividad
        (
            (_voto.get_actividad() != null) ?
                (_repoActividad.findById(_voto.get_actividad().get_idActividad()).orElse(null) != null) ?
                    _repoActividad.findById(_voto.get_actividad().get_idActividad()).orElse(null) : null
                        : (_novoVoto.getActividad() != null) ? _novoVoto.getActividad() : null
        );
        if (!compo(_novoVoto.getActividad())) throw new NullPointerException("Error: Actividad -> Voto.");
        _novoVoto.setVoto
        (
            (_voto.get_voto() != null) ? _voto.get_voto() :
                                (_novoVoto.getVoto() != null) ? _novoVoto.getVoto() : null
        );
        if (!compo(_novoVoto.getVoto())) throw new NullPointerException("Error: Voto -> Voto.");


        return _novoVoto;
    }




    // ---------------------------------------------------- Validaciones/Comprobaciones --- //
    // Comprobar si existe el perfil.
    Boolean existeUsuario(Integer _perfil)
    {
        if (_perfil == null)
            return false;
        return _repoPerfil.findById(_perfil).isPresent();
    }

    // Comprobar si existe el viaje.
    Boolean existeViaje(Integer _viaje)
    {
        if (_viaje == null)
            return false;
        return _repoViaje.findById(_viaje).isPresent();
    }

    // Comprobar si existe el perfil en el viaje.
    // esto es lo mismo que (noExisteViaje)
    Boolean existeUsuViaje(Integer _idViaje, Integer _idPerfil)
    {
        Perfil _perfil = _repoPerfil.findById(_idPerfil).orElse(null);
        if (_perfil == null)
            return false;

        Viaje _viaje = _repoViaje.findById(_idViaje).orElse(null);
        if (_viaje == null)
            return false;

        return _perfil.getViajes().contains(_viaje);
    }
}


/*
if (_dato instanceof Integer) return (Integer) _dato > 0;
else if (_dato instanceof Double) return (Double) _dato > 0;
else if (_dato instanceof Float) return (Float) _dato > 0;
else if (_dato instanceof String) return !((String) _dato).isEmpty();
else return _dato != null;
*/