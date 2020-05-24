package errorsAndExceptions.actions;

import errorsAndExceptions.universityEntities.AcademicSubject;
import errorsAndExceptions.universityEntities.Student;
import errorsAndExceptions.universityExceptions.StudentWithoutAcademicSubjectsException;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

public class StudentAction extends AverageCalculator {
    public static List<Integer> getGradesBySubject(Student student, AcademicSubject academicSubject) {
        return student.getGrades().getSubjectGrades().get(academicSubject);
    }

    public static double getAverageGradeBySubject(Student student, AcademicSubject subject) {
        LinkedList<Student> studentList = new LinkedList<>();
        studentList.add(student);
        return calculateAverageGrade(studentList, subject);
    }

    public static EnumMap<AcademicSubject, Double> getAverageGrades(Student student) throws StudentWithoutAcademicSubjectsException {
        EnumMap<AcademicSubject, Double> averageGrades = new EnumMap<>(AcademicSubject.class);

        for (AcademicSubject subject : student.getAcademicSubjects()) {
            averageGrades.put(subject, getAverageGradeBySubject(student, subject));
        }
        return averageGrades;
    }
}
