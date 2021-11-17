package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

public class ValuesDTO extends RepresentationModel<ValuesDTO> {
    private float value;

    public ValuesDTO(float value) {
        this.value = value;
    }

    public ValuesDTO() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
