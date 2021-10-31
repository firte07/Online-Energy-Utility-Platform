package ro.tuc.ds2020.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class TimeStringDTO extends RepresentationModel<TimeStringDTO> {
    //@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSS")
    //@Temporal(value = TemporalType.DATE)
    private String date;

    public TimeStringDTO() {
    }

    public TimeStringDTO(String temp) {
        this.date = temp;
    }

    public String getTemp() {
        return date;
    }

    public void setTemp(String temp) {
        this.date = temp;
    }
}
