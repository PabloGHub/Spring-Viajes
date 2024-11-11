package launcher.springviajes.DTOs;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOViaje
{
    private Integer _idViaje;
    private String _nombre;
    private String _descripcion;
    private String _password;
    private List<DTOPerfilPuro> _participantes = new ArrayList<>();
}
