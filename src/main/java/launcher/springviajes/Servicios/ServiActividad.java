package launcher.springviajes.Servicios;

import kotlin.collections.ArrayDeque;
import launcher.springviajes.DTOs.DTOActividad;
import launcher.springviajes.modelos.Actividad;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.repositorios.RepoActividad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiActividad
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
            for (int i = 0; i < _actividades.size(); i++)
            {
                DTOActividad _actividad = new DTOActividad();
                _actividad.set_idActividad(_actividades.get(i).getIdActividad());
                _actividad.set_titulo(_actividades.get(i).getTitulo());
                _actividad.set_descripcion(_actividades.get(i).getDescripcion());
                _actividad.set_precio(_actividades.get(i).getPrecio());
                _actividadesDTO.add(_actividad);
            }
        }

        return _actividadesDTO;
    }

    public Actividad darmeUno(int id)
    {
        return _repoActividad.findById(id).orElse(null);
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
}
