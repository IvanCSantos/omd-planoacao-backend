package dev.ivansantos.omd_plano_de_acao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "acoes")
@Entity
public class Acao {

  public static enum Status {
    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDO;

    public String getDescricao() {
      return switch (this) {
        case PENDENTE -> "Pendente";
        case EM_ANDAMENTO -> "Em andamento";
        case CONCLUIDO -> "Concluido";
      };
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "acao", nullable = false) // descricao
  private String acao;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;

  @Column(name = "prazo")
  private LocalDate prazo;

  @ManyToOne
  @JoinColumn(name = "plano_id", nullable = false)
  private PlanoDeAcao plano;
}
