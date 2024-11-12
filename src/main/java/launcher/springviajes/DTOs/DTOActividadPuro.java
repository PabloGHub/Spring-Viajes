package launcher.springviajes.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOActividadPuro
{
    private Integer _idActividad;
    private String _titulo;
    private String _descripcion;
    private Float _precio;
    private DTOFecha _fecha;
}
