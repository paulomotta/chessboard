package br.pro.paulomotta.api.model;

/**
 *
 * @author paulo
 */
public class PositionDTO {
    private final String coordinates;

    public String getCoordinates() {
        return coordinates;
    }

    public PositionDTO(String coordinates) {
        this.coordinates = coordinates;
    }
}
