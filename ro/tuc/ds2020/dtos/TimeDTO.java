package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

public class TimeDTO extends RepresentationModel<TimeDTO> {
    private LocalDateTime temp;

    public TimeDTO() {
    }

    public TimeDTO(LocalDateTime temp) {
        this.temp = temp;
    }

    public LocalDateTime getTemp() {
        return temp;
    }

    public void setTemp(LocalDateTime temp) {
        this.temp = temp;
    }

}
