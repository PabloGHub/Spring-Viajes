package launcher.springviajes.DTOs;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOActividad
{
    private Integer _idActividad;
    private String _titulo;
    private String _descripcion;
    private Float _precio;
    private String _fecha;
    private DTOViaje _Viaje;
}