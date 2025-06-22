package dev.ivansantos.omd_plano_de_acao.infrastructure.repository;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.ActionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionPlanRepository extends JpaRepository<ActionPlan, Integer> {

}
