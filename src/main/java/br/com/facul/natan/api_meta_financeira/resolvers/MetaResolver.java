package br.com.facul.natan.api_meta_financeira.resolvers;

import br.com.facul.natan.api_meta_financeira.entities.Meta;
import br.com.facul.natan.api_meta_financeira.services.MetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MetaResolver {
    private final MetaService metaService;

    @QueryMapping
    public List<Meta> metas(){
        return metaService.metas();
    }

    @QueryMapping
    public Meta metaPorUuid(@Argument UUID uuid){
        return metaService.metaPorUuid(uuid);
    }

    @MutationMapping
    public Meta criarMeta(@Argument String descricao, @Argument BigDecimal valorObjetivo){
        return metaService.criarMeta(descricao, valorObjetivo);
    }

    @MutationMapping
    public Meta atualizarMeta(@Argument UUID uuid, @Argument BigDecimal valorEntrada){
        return metaService.atualizarMeta(uuid, valorEntrada);
    }
}
