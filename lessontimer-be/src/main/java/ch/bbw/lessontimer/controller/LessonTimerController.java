package ch.bbw.lessontimer.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import ch.bbw.lessontimer.model.LessonTimer;
import ch.bbw.lessontimer.repository.LessonTimerRepository;

@RestController
@RequestMapping("/timer")
public class LessonTimerController {

    private final LessonTimerRepository lessonTimerRepository;

    public LessonTimerController(LessonTimerRepository lessonTimerRepository) {
        this.lessonTimerRepository = lessonTimerRepository;
    }

    @GetMapping
    public ResponseEntity<List<LessonTimer>> getAllLessonTimer() {
        List<LessonTimer> timerObjects = lessonTimerRepository.findAll();
        return ResponseEntity.ok(timerObjects);
    }

    @GetMapping("/current")
    public ResponseEntity<LessonTimer> getCurrentLessonTimer() {
        LocalTime now = LocalTime.now();
        Optional<LessonTimer> timerOptional = lessonTimerRepository
                .findAll()
                .stream()
                .filter(timer -> {
                    LocalTime start = timer.getStartTime();
                    LocalTime end = timer.getEndTime();
                    return start != null && end != null &&
                            (now.equals(start.minusMinutes(45)) || now.equals(end) ||
                                    (now.isAfter(start.minusMinutes(45)) && now.isBefore(end)));
                })
                .findFirst();

        LocalDate currentDate = LocalDate.now();
        DayOfWeek currentDay = currentDate.getDayOfWeek();
        if (timerOptional.isPresent() && timerOptional.get().getDayofweek() == currentDay) {
            LessonTimer timerObject = timerOptional.get();
            return ResponseEntity.ok(timerObject);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonTimer> getLessonTimer(@Valid @PathVariable Long id) {
        Optional<LessonTimer> lessonTimerOptional = lessonTimerRepository.findById(id);
        if (lessonTimerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LessonTimer timerObject = lessonTimerOptional.get();
        return ResponseEntity.ok(timerObject);

    }

    @PostMapping
    public ResponseEntity<LessonTimer> postLessonTimer(@Valid @RequestBody LessonTimer lessonTimer) {
        LessonTimer timerObject = lessonTimerRepository.save(lessonTimer);
        return ResponseEntity.ok(timerObject);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllLessonTimer() {
        lessonTimerRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessonTimer(@Valid @PathVariable Long id) {
        if (!lessonTimerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            lessonTimerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}