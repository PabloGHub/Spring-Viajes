package launcher.springviajes.modelos;


import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode // comprobar si dos objetos son iguales y pasar a codigo
@ToString
@Entity
@Table(name = "perfil", schema = "public", catalog = "viajes")
public class Perfil
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incremental
    @Column(name = "idusuario")
    private Integer idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "password")
    private String password;

    // Este funciona perfecto
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable
    (
        name = "usuario_viaje",
        joinColumns = @JoinColumn(name = "idusuario"),
        inverseJoinColumns = @JoinColumn(name = "idviaje")
    )
    private Set<Viaje> viajes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Perfil.class)
    @JoinTable
    (
        name = "amigo", schema = "public", catalog = "viajes",
        joinColumns = @JoinColumn(name = "idusuario"),
        inverseJoinColumns = @JoinColumn(name = "idamigo")
    )
    private Set<Perfil> amigos;
}
