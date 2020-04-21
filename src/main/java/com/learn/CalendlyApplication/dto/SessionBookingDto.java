package com.learn.CalendlyApplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NotNull
public class SessionBookingDto {
    private Integer hostId;
    private Integer guestId;
    private LocalDate date;
    private LocalTime time;
}
