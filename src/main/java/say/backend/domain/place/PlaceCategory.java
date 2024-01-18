package say.backend.domain.place;

public enum PlaceCategory{
    CHARGE("CHARGE"),
    REPAIR("REPAIR"),
    AIR_INJECTOR("AIR_INJECTOR");

    private String placeCategory;

    PlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }

    public String getPlaceCategory() {
        return this.placeCategory;
    }

}
