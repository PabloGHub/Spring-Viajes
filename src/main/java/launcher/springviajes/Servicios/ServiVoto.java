package launcher.springviajes.Servicios;

import launcher.springviajes.DTOs.DTOVoto;
import launcher.springviajes.modelos.Actividad;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.modelos.Voto;
import launcher.springviajes.repositorios.RepoVoto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiVoto extends Empaquetador
{
    private RepoVoto _repoVoto;


    public List<Voto> darmeTodo()
    {
        return _repoVoto.findAll();
    }

    public Voto darmeUno(int id)
    {
        return _repoVoto.findById(id).orElse(null);
    }
    public DTOVoto darmeUno(Perfil _perfil, Actividad _actividad)
    {
        Voto _voto = _repoVoto.findByPerfilAndActividad(_perfil, _actividad);
        if (_voto == null)
            return null;
        return empaquetar(_voto);
    }


    public Boolean eliminar(int id)
    {
        try
        {
            _repoVoto.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public DTOVoto guardar(DTOVoto _voto)
    {
        Voto _novoVoto = desempaquetar(_voto);
        _novoVoto = _repoVoto.save(_novoVoto);

        return empaquetar(_novoVoto);
    }
}
