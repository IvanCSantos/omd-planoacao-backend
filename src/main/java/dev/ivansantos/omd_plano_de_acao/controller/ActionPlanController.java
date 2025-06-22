package dev.ivansantos.omd_plano_de_acao.controller;

import dev.ivansantos.omd_plano_de_acao.api.dto.ActionPlanRequest;
import dev.ivansantos.omd_plano_de_acao.business.service.ActionPlanService;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.ActionPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action-plan")
@RequiredArgsConstructor
public class ActionPlanController {
  private final ActionPlanService actionPlanService;

  @PostMapping
  public ResponseEntity<Void> saveActionPlan(@RequestBody ActionPlanRequest request) {
    actionPlanService.saveActionPlan(request);

    return ResponseEntity.ok().build();
  }


  @GetMapping("/all")
  public ResponseEntity<List<ActionPlan>> getAllActionPlans() {
    return ResponseEntity.ok(actionPlanService.getAllActionPlans());
  }

  @GetMapping
  public ResponseEntity<ActionPlan> getActionPlanById(@RequestParam Integer id) {
    return ResponseEntity.ok(actionPlanService.getActionPlanById(id));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteActionPlanById(@RequestParam Integer id) {
    actionPlanService.deleteActionPlanById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<Void> updateActionPlanById(@RequestParam Integer id, @RequestBody ActionPlan actionPlan) {
    actionPlanService.updateActionPlanById(id, actionPlan);
    return ResponseEntity.ok().build();
  }
}
