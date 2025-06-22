package dev.ivansantos.omd_plano_de_acao.business.service;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.ActionPlan;
import dev.ivansantos.omd_plano_de_acao.infrastructure.repository.ActionPlanRepository;
import org.springframework.stereotype.Service;

@Service
public class ActionPlanService {

  private final ActionPlanRepository repository;

  public ActionPlanService(ActionPlanRepository repository) {
    this.repository = repository;
  }

  public void savePlanoDeAcao(ActionPlan actionPlan) {
    repository.saveAndFlush(actionPlan);
  }

  public ActionPlan getActionPlanById(Integer id) {
    return repository.findById(id).orElseThrow(
            () -> new RuntimeException("ID não encontrado")
    );
  }

  public void deleteActionPlanById(Integer id) {
    repository.deleteById(id);
  }

  public void updateActionPlanById(Integer id, ActionPlan updatedActionPlan) {
    ActionPlan actionPlan = getActionPlanById(id);

    actionPlan.setTitle(updatedActionPlan.getTitle() != null ? updatedActionPlan.getTitle() : actionPlan.getTitle());
    actionPlan.setGoal(updatedActionPlan.getGoal() != null ? updatedActionPlan.getGoal() : actionPlan.getGoal());
    actionPlan.setDate(updatedActionPlan.getDate() != null ? updatedActionPlan.getDate() : actionPlan.getDate());

    repository.saveAndFlush(actionPlan);
  }

  public void updateActionPlanStatus(Integer id) {
    ActionPlan actionPlan = getActionPlanById(id);

    boolean allConcluded = actionPlan.getActions().stream()
            .allMatch(acao -> acao.getStatus() == Action.Status.COMPLETED);

    ActionPlan.Status newStatus = allConcluded
            ? ActionPlan.Status.COMPLETED
            : ActionPlan.Status.PENDING;

    actionPlan.setStatus(newStatus);
    repository.saveAndFlush(actionPlan);
  }


}
