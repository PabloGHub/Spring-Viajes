package launcher.springviajes.DTOs;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


// TODO: Convertir DTOViaje en DTOViajePuro y esta en normal
//  asi como lo necesario para que vuelva ah funcioanar.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOViajeDatos
{
    private Integer _idViaje;
    private String _nombre;
    private String _descripcion;
    private String _password;
    private List<DTOPerfilPuro> _participantes = new ArrayList<>();
}
