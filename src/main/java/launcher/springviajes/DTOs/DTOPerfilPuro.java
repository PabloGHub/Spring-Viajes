package launcher.springviajes.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOPerfilPuro
{
    private Integer _idPerfil;
    private String _nombre;
    private String _password;
}
