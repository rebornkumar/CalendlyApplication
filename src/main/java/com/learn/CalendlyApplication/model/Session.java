package com.learn.CalendlyApplication.model;


import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "session")
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_bookable")
    private Boolean isBookable;

    @Column(name = "date",nullable = false)
    private String sessionDate;

    @Column(name = "time",nullable = false)
    private String sessionTime;

    @Column(name = "booked_at")
    private String sessionBookedAt;

    @Column(name = "guest_id")
    private Integer guestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;
}
