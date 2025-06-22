package dev.ivansantos.omd_plano_de_acao.controller;

import dev.ivansantos.omd_plano_de_acao.api.dto.ActionRequest;
import dev.ivansantos.omd_plano_de_acao.api.dto.ActionResponse;
import dev.ivansantos.omd_plano_de_acao.business.service.ActionService;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ActionController {
  private final ActionService actionService;

  @PostMapping("/action-plan/{actionPlanId}/action")
  public ResponseEntity<ActionResponse> createAction(@PathVariable Integer actionPlanId, @RequestBody ActionRequest request) {
    Action action = actionService.saveAction(actionPlanId, request);

    return ResponseEntity.status(HttpStatus.CREATED).body(new ActionResponse(action));
  }

  @GetMapping("/action-plan/{actionPlanId}/action")
  public ResponseEntity<List<ActionResponse>> getActionsByActionPlan(@PathVariable Integer actionPlanId) {
    List<ActionResponse> actions = actionService.getActionsByActionPlanId(actionPlanId)
            .stream()
            .map(ActionResponse::new)
            .toList();

    return ResponseEntity.ok(actions);
  }

  @GetMapping("/action/{id}")
  public ResponseEntity<ActionResponse> getActionById(@PathVariable Integer id) {
    return ResponseEntity.ok(new ActionResponse(actionService.getActionById(id)));
  }

  @PutMapping("/action/{id}")
  public ResponseEntity<ActionResponse> updateAction(@PathVariable Integer id, @RequestBody ActionRequest request) {
    return ResponseEntity.ok(new ActionResponse(actionService.updateActionById(id, request)));
  }

  @DeleteMapping("/action/{id}")
  public ResponseEntity<Void> deleteAction(@PathVariable Integer id) {
    actionService.deleteActionById(id);
    return ResponseEntity.noContent().build();
  }
}
