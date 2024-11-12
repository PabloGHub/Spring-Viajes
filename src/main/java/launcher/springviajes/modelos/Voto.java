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
    name = "voto", schema = "public", catalog = "viajes",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"idUsuario", "idActividad"})}
) // Se supone que el CONJUNTO es unico
public class Voto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvoto")
    private Integer idVoto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idusuario")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idactividad")
    private Actividad actividad;

    @Column(name = "voto")
    private Integer voto; // 0 no voto; 1 me gusta; 2 no me gusta
}
