package say.backend.domain.place;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import say.backend.domain.common.DelYn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Getter @Setter @Entity
@Table(name = "place_info")
public class PlaceInfo {
    @Column(name="place_idx")
    @Id
    private String placeIdx;

    @Column(name="place_name")
    private String placeName;

    @Column(name="address")
    private String address;

    @Column(name="address_detail")
    private String addressDetail;

    @Enumerated(EnumType.STRING)
    @Column(name="place_category")
    private PlaceCategory placeCategory;

    @Column(name="coordinate")
    private String coordinate;

    @Column(name="reg_dt")
    private LocalDateTime regDt;

    @Column(name="mod_dt")
    private LocalDateTime modDt;

    @ColumnDefault("'N'")
    @Column(name="del_yn")
    private DelYn delYn;

    @OneToMany
    private List<PlaceLink> placeLinkList = new ArrayList<PlaceLink>();
}
