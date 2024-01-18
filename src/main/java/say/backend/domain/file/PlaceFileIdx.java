package say.backend.domain.file;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PlaceFileIdx implements Serializable {
    private String placeIdx;
    private String fileIdx;
}
