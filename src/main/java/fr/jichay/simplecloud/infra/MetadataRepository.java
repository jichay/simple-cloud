package fr.jichay.simplecloud.infra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MetadataRepository extends JpaRepository<ContentMetadataEntity, String> {

}
