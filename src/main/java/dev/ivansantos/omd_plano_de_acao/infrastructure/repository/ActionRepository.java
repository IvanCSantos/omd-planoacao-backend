package dev.ivansantos.omd_plano_de_acao.infrastructure.repository;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Integer> {

  List<Action> findByActionPlanId(Integer actionPlanId);
}
