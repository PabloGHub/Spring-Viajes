package launcher.springviajes.Servicios;

import launcher.springviajes.DTOs.*;
import launcher.springviajes.modelos.Actividad;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.modelos.Viaje;
import launcher.springviajes.modelos.Voto;
import launcher.springviajes.repositorios.RepoViaje;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiViaje extends Empaquetador
{
    private RepoViaje _repoViaje;
    private ServiPerfil _serviPerfil;
    private ServiActividad _serviActividad;
    private ServiVoto _serviVoto;
    //private Empaquetador _emp;

    public List<DTOViajePuro> darmeTodo()
    {
        List<Viaje> _viajes = _repoViaje.findAll();
        List<DTOViajePuro> _viajesODT = new ArrayList<>();

        if (_viajes.size() < 0)
            return null;
        else
        {
            for (Viaje _viaje : _viajes)
            {
                _viajesODT.add(empaquetarPuro(_viaje));
            }
        }

        return _viajesODT;
    }


    public DTOViajePuro darmeUno(int id)
    {
        Viaje _viaje = _repoViaje.findById(id).orElse(null);

        if (_viaje == null)
            return null;

        return empaquetarPuro(_viaje);
    }
    public Viaje darmeUnoViaje(int id)
    {
        Viaje _viaje = _repoViaje.findById(id).orElse(null);

        if (_viaje == null)
            return null;

        return _viaje;
    }


    public Boolean eliminar(int id)
    {
        try
        {
            _repoViaje.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public DTOViajePuro guardar(DTOViajePuro _viaje)
    {
        Viaje _NovoViaje = desempaquetar(_viaje);
        _NovoViaje = _repoViaje.save(_NovoViaje);

        return empaquetarPuro(_NovoViaje);
    }


    // Me acabo de dar cuenta de que podría haber creado un Servicio separado.
    public DTOPerfil annadirParticipanteViaje(int idViaje, int idPerfil)
    {
        DTOViajePuro _viaje = this.darmeUno(idViaje);
        DTOPerfil _perfil = _serviPerfil.darmeUno(idPerfil);

        if (_viaje == null || _perfil == null)
            throw new NullPointerException("Viaje o Perfil no encontrado");

        _perfil.get_viajes().add(_viaje);
        _serviPerfil.guardar(_perfil);

        return _perfil;
    }

    public List<DTOPerfilPuro> verParticipantesViaje(int idViaje)
    {
        return _serviPerfil.darmeTodo().stream()
                .filter(p -> p.get_viajes().stream().anyMatch(v -> v.get_idViaje() == idViaje))
                .map(this::empaquetar)
                .toList();
    }

    public DTOViaje eliminarParticipanteViaje(int idViaje, int idPerfil)
    {
        DTOViajePuro _viaje = this.darmeUno(idViaje);
        DTOPerfil _perfil = _serviPerfil.darmeUno(idPerfil);
        _perfil.get_viajes().remove(_viaje);
        _serviPerfil.guardar(_perfil);

        return empaquetar(desempaquetar(_viaje));
    }


    public List<DTOActividad> verActividades(int _viaje)
    {
        return _serviActividad.darmeTodo().stream()
                .filter(a -> a.get_Viaje().get_idViaje() == _viaje)
                .toList();
    }

    public DTOActividad proponerActividad(DTOActividad _actividad, int _perfil)
    {
        DTOViajePuro _viaje = this.darmeUno(_actividad.get_Viaje().get_idViaje());
        if (_viaje == null) return null;

        _actividad.set_Viaje(_viaje);
        return _serviActividad.guardar(_actividad);
    }


    public List<DTOViajePuro> listarViaje(int idUsuario)
    {
        return _serviPerfil.darmeUno(idUsuario).get_viajes();
    }



    public DTOVotoPuro votarActividad(int _act, int _usu, int _voto)
    {
        Actividad _actividad = desempaquetar(_serviActividad.darmeUno(_act));
        Perfil _perfil = desempaquetar(_serviPerfil.darmeUno(_usu));

        if (_actividad == null || _perfil == null)
            return null;


        DTOVoto _nuevoVoto = _serviVoto.darmeUno(_perfil, _actividad);
        if (_nuevoVoto == null)
        {
            _nuevoVoto = new DTOVoto();
            _nuevoVoto.set_actividad(empaquetarPuro(_actividad));
            _nuevoVoto.set_perfil(empaquetarPuro(_perfil));
        }

        _nuevoVoto.set_voto(_voto);
        return empaquetar(_serviVoto.guardar(_nuevoVoto));
    }
}