package launcher.springviajes.DTOs;

import java.util.ArrayList;
import java.util.List;

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
    private DTOFecha _fecha;
    private DTOViajePuro _Viaje;
    private List<DTOVotoPuro> _Votos = new ArrayList<>();
}