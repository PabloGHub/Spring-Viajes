package launcher.springviajes.repositorios;

import launcher.springviajes.modelos.Actividad;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.modelos.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoVoto extends JpaRepository<Voto, Integer>
{
    Voto findByPerfilAndActividad(Perfil perfil, Actividad actividad);
    List<Voto> findByActividad(Actividad actividad);
    /*UN MONTON DE NADA*/
}
