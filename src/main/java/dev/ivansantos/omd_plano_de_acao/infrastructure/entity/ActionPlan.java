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
@Table(name = "action_plans")
@Entity
public class ActionPlan {

  public static enum Status {
    PENDING,
    COMPLETED;

    public String getDescription() {
      return switch (this) {
        case PENDING -> "Pendente";
        case COMPLETED -> "Concluido";
      };
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "goal")
  private String goal;

  @Column(name = "date")
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;

  @OneToMany(mappedBy = "actionPlan", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Action> actions;

}
