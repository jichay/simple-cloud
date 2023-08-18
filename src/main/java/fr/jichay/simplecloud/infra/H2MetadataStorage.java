package fr.jichay.simplecloud.infra;

import fr.jichay.simplecloud.domain.storage.model.ContentMetadata;
import fr.jichay.simplecloud.domain.storage.service.MetadataStorageProvider;
import org.springframework.stereotype.Service;

@Service
public class H2MetadataStorage implements MetadataStorageProvider {

    private final MetadataRepository metadataRepository;

    public H2MetadataStorage(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    @Override
    public ContentMetadata store(ContentMetadata metadata) {
        var entity = ContentMetadataEntity.from(metadata);
        var saved = metadataRepository.save(entity);
        return ContentMetadata.from(saved);
    }

    @Override
    public ContentMetadata getMetadata(String key) {
        var entity = metadataRepository.getReferenceById(key);
        return ContentMetadata.from(entity);
    }
}
