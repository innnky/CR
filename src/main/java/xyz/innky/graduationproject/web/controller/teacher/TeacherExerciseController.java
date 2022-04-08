package xyz.innky.graduationproject.web.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.web.service.ExerciseService;

@RestController
@RequestMapping("/teacher/exercise")
public class TeacherExerciseController {
    @Autowired
    private ExerciseService exerciseService;
}
