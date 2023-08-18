package fr.jichay.simplecloud.infra;

import fr.jichay.simplecloud.domain.storage.model.ContentMetadata;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "metadata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentMetadataEntity {

    @Id
    private String id;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private long size;

    public static ContentMetadataEntity from(ContentMetadata metadata) {
        return new ContentMetadataEntity(
                metadata.storageKey(),
                metadata.fileName(),
                metadata.contentType(),
                metadata.size()
        );
    }

}
