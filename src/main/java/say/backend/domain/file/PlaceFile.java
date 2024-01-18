package say.backend.domain.file;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import say.backend.domain.place.PlaceInfo;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Getter @Setter @Entity
@Table(name = "place_file")
public class PlaceFile {
    @EmbeddedId
    private PlaceFileIdx placeFileIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="place_idx")
    @MapsId("placeIdx")
    private PlaceInfo placeIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_idx")
    @MapsId("fileIdx")
    private FileInfo fileIdx;

}
