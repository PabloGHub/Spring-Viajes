package launcher.springviajes.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import launcher.springviajes.modelos.Viaje;

@Repository
public interface RepoViaje extends JpaRepository<Viaje, Integer>
{/*UN MONTON DE NADA*/}
