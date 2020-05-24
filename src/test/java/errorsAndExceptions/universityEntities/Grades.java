package errorsAndExceptions.universityEntities;

import errorsAndExceptions.universityExceptions.GradeOutOfRangeException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class Grades {
    private EnumMap<AcademicSubject, List<Integer>> subjectGrades = new EnumMap<>(AcademicSubject.class);

    public Grades(EnumSet<AcademicSubject> academicSubjects) {
        for (AcademicSubject academicSubject : academicSubjects) {
            subjectGrades.put(academicSubject, new ArrayList<>());
        }
    }

    public EnumMap<AcademicSubject, List<Integer>> getSubjectGrades() {
        return subjectGrades;
    }

    public void addSubject(AcademicSubject academicSubject) {
        if (!subjectGrades.containsKey(academicSubject)) {
            subjectGrades.put(academicSubject, new ArrayList<>());
        }
    }

    public void setGrade(AcademicSubject academicSubject, int grade)
            throws GradeOutOfRangeException {
        if ((grade >= 0) && (grade <= 10)) {
            List<Integer> grades = subjectGrades.get(academicSubject);
            grades.add(grade);
            subjectGrades.put(academicSubject, grades);
        } else {
            throw new GradeOutOfRangeException("Оценка " + grade + " является недопустимой!");
        }
    }

    @Override
    public String toString() {
        return subjectGrades.toString();
    }

}
