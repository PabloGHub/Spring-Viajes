package launcher.springviajes.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOViaje
{
    private Integer _idViaje;
    private String _nombre;
    private String _descripcion;
    private String _contraseña; // Pongamos a prueba la BD y SpringBoot
}
