package launcher.springviajes.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOFecha
{
    private int _dia;
    private int _mes;
    private int _anno;

    public String toString()
    {
        return _dia + "/" + _mes + "/" + _anno;
    }
}
