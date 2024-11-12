package launcher.springviajes.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOVoto
{
    private Integer _idVoto;
    private DTOPerfilPuro _perfil;
    private DTOActividadPuro _actividad;
    private Integer _voto;
}
