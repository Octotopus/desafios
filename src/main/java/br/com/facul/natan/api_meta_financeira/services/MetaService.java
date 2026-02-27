package br.com.facul.natan.api_meta_financeira.services;

import br.com.facul.natan.api_meta_financeira.entities.Meta;
import br.com.facul.natan.api_meta_financeira.repositories.MetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MetaService {
    private final MetaRepository metaRepository;

    public List<Meta> metas(){
        return metaRepository.findAll();
    }

    public Meta metaPorUuid(UUID uuid){
        return metaRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Não foi possível localizar a meta!"));
    }

    public Meta criarMeta(String descricao, BigDecimal valorObjetivo){
        Meta meta = new Meta();

        meta.setUuid(UUID.randomUUID());
        meta.setDescricao(descricao);
        meta.setValorObjetivo(valorObjetivo);
        meta.setValorAtual(BigDecimal.valueOf(0));
        meta.setDataCriacao(LocalDate.now());
        meta.setPercentual(Float.valueOf(0));
        meta.setConcluida(Boolean.FALSE);

        return metaRepository.save(meta);
    }

    public Meta atualizarMeta(UUID uuid, BigDecimal valorEntrada){
        Meta meta = metaRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Não foi possível localizar a meta!"));

        BigDecimal valorAtual =  meta.getValorAtual().add(valorEntrada);

        BigDecimal percentual;

        meta.setValorAtual(valorAtual);
        percentual = (meta.getValorAtual().divide(meta.getValorObjetivo(), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));

        meta.setPercentual(percentual.floatValue());

        if (meta.getValorAtual().compareTo(meta.getValorObjetivo()) >= 0 ){
            meta.setConcluida(Boolean.TRUE);
        }

        return metaRepository.save(meta);
    }
}
