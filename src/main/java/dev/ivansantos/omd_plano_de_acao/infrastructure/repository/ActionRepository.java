package dev.ivansantos.omd_plano_de_acao.infrastructure.repository;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Integer> {

}
