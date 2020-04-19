package com.learn.CalendlyApplication.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class BookingDetailsDto {
    @NotEmpty
    private String guestName;
    @NotEmpty
    private String hostName;
    private LocalDate sessionDate;
    private LocalTime sessionTime;
    private String duration = "60 minutes";
}
