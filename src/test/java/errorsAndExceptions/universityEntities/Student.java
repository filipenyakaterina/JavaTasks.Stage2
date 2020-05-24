package errorsAndExceptions.universityEntities;

import errorsAndExceptions.universityExceptions.GradeOutOfRangeException;
import errorsAndExceptions.universityExceptions.StudentWithoutAcademicSubjectsException;

import java.util.EnumSet;
import java.util.Objects;

public class Student {
    private static long studentsCount = 0;

    private String name;
    private String surname;
    private long studentIDNumber;
    private EnumSet<AcademicSubject> academicSubjects;
    private Grades grades;

    public Student(String name, String surname, EnumSet<AcademicSubject> academicSubjects) {
        this.name = name;
        this.surname = surname;
        this.academicSubjects = academicSubjects;
        grades = new Grades(this.academicSubjects);
        studentIDNumber = studentsCount;
        studentsCount++;
    }

    public long getStudentIDNumber() {
        return studentIDNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public EnumSet<AcademicSubject> getAcademicSubjects()
            throws StudentWithoutAcademicSubjectsException {
        if (academicSubjects.isEmpty()) {
            throw new StudentWithoutAcademicSubjectsException("У студента " +
                    getName() + " " + getSurname() + " с номером студенческого билета "
                    + getStudentIDNumber() + " отсутствуют академические предметы!");
        }
        return academicSubjects;
    }

    public boolean isStudySubject(AcademicSubject academicSubject) {
        return academicSubjects.contains(academicSubject);
    }

    public void setAcademicSubject(AcademicSubject subject) {
        academicSubjects.add(subject);
        grades.addSubject(subject);
    }

    public Grades getGrades() {
        return grades;
    }

    public void setGrade(AcademicSubject subject, int grade)
            throws GradeOutOfRangeException, StudentWithoutAcademicSubjectsException {
        if (!getAcademicSubjects().contains(subject)) {
            setAcademicSubject(subject);
        }
        grades.setGrade(subject, grade);
    }

    @Override
    public String toString() {
        return "Student " + studentIDNumber + ' ' + name + ' ' +
                surname + ' ' + grades + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentIDNumber == student.studentIDNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentIDNumber);
    }
}
