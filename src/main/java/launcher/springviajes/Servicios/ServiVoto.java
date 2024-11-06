package launcher.springviajes.Servicios;

import launcher.springviajes.modelos.Voto;
import launcher.springviajes.repositorios.RepoVoto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiVoto
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
}
