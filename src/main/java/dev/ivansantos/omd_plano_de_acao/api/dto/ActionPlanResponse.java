package dev.ivansantos.omd_plano_de_acao.api.dto;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.ActionPlan;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ActionPlanResponse {
  private Integer id;
  private String title;
  private String goal;
  private LocalDate creationDate;
  private ActionPlan.Status status;
  private List<ActionResponse> actions;

  public ActionPlanResponse(ActionPlan entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.goal = entity.getGoal();
    this.creationDate = entity.getCreationDate();
    this.status = entity.getStatus();
    this.actions = entity.getActions()
            .stream()
            .map(ActionResponse::new)
            .collect(Collectors.toList());
  }

}
