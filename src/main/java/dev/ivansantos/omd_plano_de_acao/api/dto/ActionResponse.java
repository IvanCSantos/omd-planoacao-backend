package dev.ivansantos.omd_plano_de_acao.api.dto;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ActionResponse {
  private Integer id;
  private String title;
  private Action.Status status;
  private LocalDate dueDate;

  public ActionResponse(Action entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.status = entity.getStatus();
    this.dueDate = entity.getDueDate();
  }
}
