package errorsAndExceptions.actions;

import errorsAndExceptions.universityEntities.AcademicSubject;
import errorsAndExceptions.universityEntities.Student;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AverageCalculator {
    public static Double calculateAverageGrade(List<Student> students, AcademicSubject subject) {
        List<Integer> subjectGrades = students.stream().filter(student -> student.isStudySubject(subject)).
                map(student -> StudentAction.getGradesBySubject(student, subject)).
                flatMap(Collection::stream).collect(Collectors.toList());

        Integer sumOfGrades = subjectGrades.stream().reduce(Integer::sum).orElse(0);
        return !subjectGrades.isEmpty() ? (sumOfGrades / (double) subjectGrades.size()) : sumOfGrades;
    }
}
