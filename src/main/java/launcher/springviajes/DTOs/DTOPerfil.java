package launcher.springviajes.DTOs;

import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOPerfil
{
    private Integer _idPerfil;
    private String _nombre;
    private String _password;
    private List<DTOViajePuro> _viajes = new ArrayList<>();

}
