package launcher.springviajes.repositorios;

import launcher.springviajes.modelos.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoGasto extends JpaRepository<Gasto, Integer>
{/*UN MONTON DE NADA*/}
