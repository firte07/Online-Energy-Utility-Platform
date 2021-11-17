package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class TimeDTO extends RepresentationModel<TimeDTO> {

    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime date;

    public TimeDTO() {
    }

    public TimeDTO(LocalDateTime temp) {
        this.date = temp;
    }

    public LocalDateTime getTemp() {
        return date;
    }

    public void setTemp(LocalDateTime temp) {
        this.date = temp;
    }

}
