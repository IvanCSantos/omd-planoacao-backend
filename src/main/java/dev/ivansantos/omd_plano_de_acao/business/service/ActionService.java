package dev.ivansantos.omd_plano_de_acao.business.service;

import dev.ivansantos.omd_plano_de_acao.api.dto.ActionRequest;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.ActionPlan;
import dev.ivansantos.omd_plano_de_acao.infrastructure.repository.ActionRepository;
import dev.ivansantos.omd_plano_de_acao.infrastructure.repository.ActionPlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActionService {

  private final ActionRepository repository;
  private final ActionPlanRepository actionPlanRepository;
  private final ActionPlanService actionPlanService;

  public ActionService(ActionRepository repository, ActionPlanRepository actionPlanRepository, ActionPlanService actionPlanService) {
    this.repository = repository;
    this.actionPlanRepository = actionPlanRepository;
    this.actionPlanService = actionPlanService;
  }

  public Action saveAction(Integer actionPlanId, ActionRequest request) {
    ActionPlan actionPlan = actionPlanRepository.findById(actionPlanId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ActionPlan ID " + actionPlanId + " não encontrado"));

    Action action = Action.builder()
            .title(request.getTitle())
            .status(request.getStatus() != null ? request.getStatus() : Action.Status.PENDING)
            .dueDate(request.getDueDate())
            .actionPlan(actionPlan)
            .build();

    Action savedAction = repository.saveAndFlush(action);

    actionPlanService.updateActionPlanStatus(actionPlanId);

    return savedAction;
  }

  public List<Action> getActionsByActionPlanId(Integer actionPlanId) {
    if(!actionPlanRepository.existsById(actionPlanId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ActionPlan ID " + actionPlanId + " não encontrado");
    }
    return repository.findByActionPlanId(actionPlanId);
  }

  public Action getActionById(Integer id) {
    return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Action ID " + id + " não encontrado"));
  }

  public Action updateActionById(Integer id, ActionRequest request) {
    Action action = getActionById(id);

    action.setTitle(request.getTitle() != null ? request.getTitle() : action.getTitle());
    action.setStatus(request.getStatus() != null ? request.getStatus() : action.getStatus());
    action.setDueDate(request.getDueDate() != null ? request.getDueDate() : action.getDueDate());

    Action updatedAction = repository.saveAndFlush(action);

    actionPlanService.updateActionPlanStatus(action.getActionPlan().getId());

    return updatedAction;
  }

  public void deleteActionById(Integer id) {
    Action action = getActionById(id);
    Integer actionPlanId = action.getActionPlan().getId();

    repository.delete(action);

    actionPlanService.updateActionPlanStatus(actionPlanId);
  }
}
