package ch.bbw.lessontimer.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class LessonTimer {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalTime startTime;

    public LessonTimer() {
    }

    public LessonTimer(Long id, @NotNull String name, @NotNull LocalTime startTime,
            @NotNull LocalTime endTime, @NotNull DayOfWeek dayofweek) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayofweek = dayofweek;
    }

    @NotNull
    private LocalTime endTime;

    @NotNull
    private DayOfWeek dayofweek;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public DayOfWeek getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(DayOfWeek dayofweek) {
        this.dayofweek = dayofweek;
    }
}
