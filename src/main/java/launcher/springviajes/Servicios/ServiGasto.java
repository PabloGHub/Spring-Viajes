package launcher.springviajes.Servicios;

import launcher.springviajes.modelos.Gasto;
import launcher.springviajes.repositorios.RepoGasto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiGasto
{
    private RepoGasto _repoGasto;


    public List<Gasto> darmeTodo()
    {
        return _repoGasto.findAll();
    }

    public Gasto darmeUno(int id)
    {
        return _repoGasto.findById(id).orElse(null);
    }

    public Boolean eliminar(int id)
    {
        try
        {
            _repoGasto.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}