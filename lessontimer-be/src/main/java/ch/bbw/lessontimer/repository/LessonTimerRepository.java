package ch.bbw.lessontimer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.bbw.lessontimer.model.LessonTimer;

public interface LessonTimerRepository extends JpaRepository<LessonTimer, Long> {

}
