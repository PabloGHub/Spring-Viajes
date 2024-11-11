package launcher.springviajes.Servicios;

import kotlin.collections.ArrayDeque;
import launcher.springviajes.DTOs.DTOActividad;
import launcher.springviajes.modelos.Actividad;
import launcher.springviajes.repositorios.RepoActividad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiActividad extends Empaquetador
{
    private RepoActividad _repoActividad;


    public List<DTOActividad> darmeTodo()
    {
        List<Actividad> _actividades = _repoActividad.findAll();
        List<DTOActividad> _actividadesDTO = new ArrayDeque<>();

        if (_actividades.size() < 0)
            return null;
        else
        {
            for (Actividad _actividad : _actividades)
            {
                _actividadesDTO.add(empaquetar(_actividad));
            }
        }

        return _actividadesDTO;
    }

    public DTOActividad darmeUno(int id)
    {
        Actividad _Act = _repoActividad.findById(id).orElse(null);

        if (_Act == null)
            return null;

        return empaquetar(_Act);
    }

    public Boolean eliminar(int id)
    {
        try
        {
            _repoActividad.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }



    public DTOActividad guardar(DTOActividad _dtoActividad)
    {
        Actividad _novoActividad = desempaquetar(_dtoActividad);
        _novoActividad = _repoActividad.save(_novoActividad);

        return empaquetar(_novoActividad);
    }
}
