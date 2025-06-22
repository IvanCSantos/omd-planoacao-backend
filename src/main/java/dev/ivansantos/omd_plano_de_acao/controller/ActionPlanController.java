package dev.ivansantos.omd_plano_de_acao.controller;

import dev.ivansantos.omd_plano_de_acao.api.dto.ActionPlanRequest;
import dev.ivansantos.omd_plano_de_acao.api.dto.ActionPlanResponse;
import dev.ivansantos.omd_plano_de_acao.business.service.ActionPlanService;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.ActionPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action-plan")
@RequiredArgsConstructor
public class ActionPlanController {
  private final ActionPlanService actionPlanService;

  @PostMapping
  public ResponseEntity<ActionPlanResponse> createActionPlan(@RequestBody ActionPlanRequest request) {
    ActionPlan actionPlan = actionPlanService.saveActionPlan(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(new ActionPlanResponse(actionPlan));
  }


  @GetMapping("/all")
  public ResponseEntity<List<ActionPlanResponse>> getAllActionPlans() {
    List<ActionPlanResponse> actionPlans = actionPlanService.getAllActionPlans()
            .stream()
            .map(ActionPlanResponse::new)
            .toList();
    return ResponseEntity.ok(actionPlans);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ActionPlanResponse> getActionPlanById(@PathVariable Integer id) {
    return ResponseEntity.ok(new ActionPlanResponse(actionPlanService.getActionPlanById(id)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteActionPlanById(@PathVariable Integer id) {
    actionPlanService.deleteActionPlanById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ActionPlanResponse> updateActionPlanById(@PathVariable Integer id, @RequestBody ActionPlanRequest request) {
    ActionPlan actionPlan = actionPlanService.updateActionPlanById(id, request);
    return ResponseEntity.ok(new ActionPlanResponse(actionPlan));
  }
}
