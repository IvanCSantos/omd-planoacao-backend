package dev.ivansantos.omd_plano_de_acao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "planos_de_acao")
@Entity
public class PlanoDeAcao {

  public static enum Status {
    PENDENTE,
    CONCLUIDO;

    public String getDescricao() {
      return switch (this) {
        case PENDENTE -> "Pendente";
        case CONCLUIDO -> "Concluido";
      };
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "titulo", nullable = false)
  private String titulo;

  @Column(name = "objetivo")
  private String objetivo;

  @Column(name = "data")
  private LocalDate data;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;

  @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Acao> acoes;

}
