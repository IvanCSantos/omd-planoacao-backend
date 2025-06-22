package dev.ivansantos.omd_plano_de_acao.business.service;

import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.Acao;
import dev.ivansantos.omd_plano_de_acao.infrastructure.entity.PlanoDeAcao;
import dev.ivansantos.omd_plano_de_acao.infrastructure.repository.PlanoDeAcaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PlanoDeAcaoService {

  private final PlanoDeAcaoRepository repository;

  public PlanoDeAcaoService(PlanoDeAcaoRepository repository) {
    this.repository = repository;
  }

  public void savePlanoDeAcao(PlanoDeAcao planoDeAcao) {
    repository.saveAndFlush(planoDeAcao);
  }

  public PlanoDeAcao getPlanoDeAcaoById(Integer id) {
    return repository.findById(id).orElseThrow(
            () -> new RuntimeException("ID não encontrado")
    );
  }

  public void deletePlanoDeAcaoById(Integer id) {
    repository.deleteById(id);
  }

  public void updatePlanoDeAcaoById(Integer id, PlanoDeAcao updatedPlanoDeAcao) {
    PlanoDeAcao planoDeAcao = getPlanoDeAcaoById(id);

    planoDeAcao.setTitulo(updatedPlanoDeAcao.getTitulo() != null ? updatedPlanoDeAcao.getTitulo() : planoDeAcao.getTitulo());
    planoDeAcao.setObjetivo(updatedPlanoDeAcao.getObjetivo() != null ? updatedPlanoDeAcao.getObjetivo() : planoDeAcao.getObjetivo());
    planoDeAcao.setData(updatedPlanoDeAcao.getData() != null ? updatedPlanoDeAcao.getData() : planoDeAcao.getData());

    repository.saveAndFlush(planoDeAcao);
  }

  public void updatePlanoDeAcaoStatus(Integer id) {
    PlanoDeAcao planoDeAcao = getPlanoDeAcaoById(id);

    boolean allConcluded = planoDeAcao.getAcoes().stream()
            .allMatch(acao -> acao.getStatus() == Acao.Status.CONCLUIDO);

    PlanoDeAcao.Status newStatus = allConcluded
            ? PlanoDeAcao.Status.CONCLUIDO
            : PlanoDeAcao.Status.PENDENTE;

    planoDeAcao.setStatus(newStatus);
    repository.saveAndFlush(planoDeAcao);
  }


}
