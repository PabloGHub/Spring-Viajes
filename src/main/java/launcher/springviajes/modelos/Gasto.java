package launcher.springviajes.modelos;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table
(
    name = "gasto", schema = "public", catalog = "viajes",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"idUsuario", "idActividad"})}
) // Se supone que el CONJUNTO es unico
public class Gasto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdGasto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idusuario")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idactividad")
    private Actividad actividad;

    @Column(name = "dinero")
    private Double dinero;
}
