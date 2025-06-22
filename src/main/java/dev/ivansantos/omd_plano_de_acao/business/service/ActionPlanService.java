package dev.ivansantos.omd_plano_de_acao.business.service;

import dev.ivansantos.omd_plano_de_acao.api.dto.ActionPlanRequest;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.ActionPlan;
import dev.ivansantos.omd_plano_de_acao.infrastructure.repository.ActionPlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActionPlanService {

  private final ActionPlanRepository repository;

  public ActionPlanService(ActionPlanRepository repository) {
    this.repository = repository;
  }

  public ActionPlan saveActionPlan(ActionPlanRequest request) {
    ActionPlan actionPlan = ActionPlan.builder()
                    .title(request.getTitle())
            .goal(request.getGoal())
            .creationDate(LocalDate.now())
            .status(ActionPlan.Status.PENDING)
            .actions(List.of())
                            .build();

    return repository.saveAndFlush(actionPlan);
  }

  public List<ActionPlan> getAllActionPlans() {
    return repository.findAll();
  }

  public ActionPlan getActionPlanById(Integer id) {
    return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ActionPlan ID " + id + " não encontrado")
    );
  }

  public void deleteActionPlanById(Integer id) {
    ActionPlan actionPlan = getActionPlanById(id);
    repository.delete(actionPlan);
  }

  public ActionPlan updateActionPlanById(Integer id, ActionPlanRequest request) {
    ActionPlan actionPlan = getActionPlanById(id);

    actionPlan.setTitle(request.getTitle() != null ? request.getTitle() : actionPlan.getTitle());
    actionPlan.setGoal(request.getGoal() != null ? request.getGoal() : actionPlan.getGoal());

    ActionPlan updatedActionPlan =  repository.saveAndFlush(actionPlan);

    return updatedActionPlan;
  }

  public ActionPlan updateActionPlanStatus(Integer id) {
    ActionPlan actionPlan = getActionPlanById(id);

    boolean allConcluded = actionPlan.getActions().stream()
            .allMatch(acao -> acao.getStatus() == Action.Status.COMPLETED);

    ActionPlan.Status newStatus = allConcluded
            ? ActionPlan.Status.COMPLETED
            : ActionPlan.Status.PENDING;

    actionPlan.setStatus(newStatus);
    return repository.saveAndFlush(actionPlan);
  }


}
