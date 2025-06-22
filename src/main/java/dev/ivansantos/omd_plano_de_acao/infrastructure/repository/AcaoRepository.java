package dev.ivansantos.omd_plano_de_acao.infrastructure.repository;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Acao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcaoRepository extends JpaRepository<Acao, Integer> {

}
