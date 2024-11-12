package launcher.springviajes.repositorios;

import launcher.springviajes.modelos.Actividad;
import launcher.springviajes.modelos.Perfil;
import launcher.springviajes.modelos.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoVoto extends JpaRepository<Voto, Integer>
{
    Voto findByPerfilAndActividad(Perfil perfil, Actividad actividad);
    /*UN MONTON DE NADA*/
}
