package launcher.springviajes.Servicios;

import launcher.springviajes.DTOs.DTOPerfil;
import launcher.springviajes.DTOs.DTOPerfilPuro;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.repositorios.RepoPerfil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@AllArgsConstructor
public class ServiPerfil extends Empaquetador
{
    private RepoPerfil _repoPerfil;
    //private Empaquetador _emp;

    public List<DTOPerfil> darmeTodo()
    {
        List<Perfil> _perfiles = _repoPerfil.findAll();

        return _perfiles.isEmpty() ? null :
                _perfiles.stream()
                .map(this::empaquetar)
                .toList();
    }

    public DTOPerfil darmeUno(int id)
    {
        Perfil _perfil = _repoPerfil.findById(id).orElse(null);

        if (_perfil == null)
            return null;

        return empaquetar(_perfil);
    }
    public Perfil darmeUnoPerfil(int id)
    {
        Perfil _perfil = _repoPerfil.findById(id).orElse(null);

        if (_perfil == null)
            return null;

        return _perfil;
    }


    public Boolean eliminar(int id)
    {
        try
        {
            _repoPerfil.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public DTOPerfil guardar(DTOPerfil _perfil)
    {
        Perfil _NovoPerfil = desempaquetar(_perfil);
        _NovoPerfil = _repoPerfil.save(_NovoPerfil);

        return empaquetar(_NovoPerfil);
    }

    public List<DTOPerfilPuro> listarAmigos(int id)
    {
        List<DTOPerfilPuro> _amigos = new ArrayList<>(darmeUno(id).get_amigos());

        darmeTodo().forEach(p ->
        {
            p.get_amigos()
                    .stream()
                    .filter(a -> a.get_idPerfil() == id && a.get_idPerfil() != p.get_idPerfil())
                    .forEach(_amigos::add);
        });

        return _amigos;
    }





    public DTOPerfilPuro encuentraUno(DTOPerfilPuro _perfil)
    {
        return _repoPerfil.findAll()
                .stream()
                .filter(p -> p.getNombre().equals(_perfil.get_nombre()))
                .filter(p -> p.getPassword().equals(_perfil.get_password()))
                .findFirst()
                .map(this::empaquetarPuro)
                .orElse(null);
    }
}