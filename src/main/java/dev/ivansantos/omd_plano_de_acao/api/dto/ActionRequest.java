package dev.ivansantos.omd_plano_de_acao.api.dto;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActionRequest {
  private String title;
  private Status status;
  private LocalDate dueDate;

}
