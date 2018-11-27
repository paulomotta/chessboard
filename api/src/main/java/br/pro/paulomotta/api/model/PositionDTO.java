package br.pro.paulomotta.api.model;

/**
 * This is a DTO to return the coordinates to the web client
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

    @Override
    public String toString() {
        return "PositionDTO{" + "coordinates=" + coordinates + '}';
    }
}
