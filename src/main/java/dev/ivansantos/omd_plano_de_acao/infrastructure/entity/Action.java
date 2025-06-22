package dev.ivansantos.omd_plano_de_acao.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "actions")
@Entity
public class Action {

  public static enum Status {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    public String getDescription() {
      return switch (this) {
        case PENDING -> "Pendente";
        case IN_PROGRESS -> "Em andamento";
        case COMPLETED -> "Concluido";
      };
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;

  @Column(name = "due_date")
  private LocalDate dueDate;

  @ManyToOne
  @JoinColumn(name = "action_plan_id", nullable = false)
  private ActionPlan actionPlan;
}
