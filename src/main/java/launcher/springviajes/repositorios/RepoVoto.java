package launcher.springviajes.repositorios;

import launcher.springviajes.modelos.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoVoto extends JpaRepository<Voto, Integer>
{/*UN MONTON DE NADA*/}
