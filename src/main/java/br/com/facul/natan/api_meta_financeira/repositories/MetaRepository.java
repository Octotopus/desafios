package br.com.facul.natan.api_meta_financeira.repositories;

import br.com.facul.natan.api_meta_financeira.entities.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {
    Optional<Meta> findByUuid(UUID uuid);
}
