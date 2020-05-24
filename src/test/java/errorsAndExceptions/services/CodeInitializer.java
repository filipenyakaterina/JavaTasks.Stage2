package errorsAndExceptions.services;

import errorsAndExceptions.universityEntities.AcademicSubject;
import errorsAndExceptions.universityEntities.Faculty;
import errorsAndExceptions.universityEntities.Group;
import errorsAndExceptions.universityEntities.Student;
import errorsAndExceptions.universityExceptions.*;

import java.util.EnumSet;

public class CodeInitializer extends Initializer {

    @Override
    protected void setFaculties() {
        university.setFaculty(new Faculty("Biological"));
        university.setFaculty(new Faculty("Linguistic"));
        university.setFaculty(new Faculty("Mathematical"));
    }

    @Override
    protected void setGroups() throws UniversityWithoutFacultiesException {
        university.getFaculties().get(0).setGroup("B001");
        university.getFaculties().get(1).setGroup("L001");
        university.getFaculties().get(1).setGroup("L002");
        university.getFaculties().get(2).setGroup("M001");
        university.getFaculties().get(2).setGroup("M002");
    }

    @Override
    protected void setStudents() throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException {
        university.getFaculties().get(0).getGroup("B001").setStudent(new Student("Petr", "Olegov",
                EnumSet.of(AcademicSubject.BOTANY, AcademicSubject.ANATOMY, AcademicSubject.PHILOSOPHY)));
        university.getFaculties().get(0).getGroup("B001").setStudent(new Student("Petr", "Vadimov",
                EnumSet.of(AcademicSubject.BOTANY, AcademicSubject.ANATOMY, AcademicSubject.PHILOSOPHY)));
        university.getFaculties().get(0).getGroup("B001").setStudent(new Student("Vadim", "Olegov",
                EnumSet.of(AcademicSubject.BOTANY, AcademicSubject.ANATOMY, AcademicSubject.PHILOSOPHY)));

        university.getFaculties().get(1).getGroup("L001").setStudent(new Student("Ivan", "Ivanov",
                EnumSet.of(AcademicSubject.ENGLISH_LITERATURE, AcademicSubject.ENGLISH, AcademicSubject.GERMAN, AcademicSubject.PHILOSOPHY)));
        university.getFaculties().get(1).getGroup("L001").setStudent(new Student("Petr", "Petrov",
                EnumSet.of(AcademicSubject.ENGLISH_LITERATURE, AcademicSubject.ENGLISH, AcademicSubject.GERMAN, AcademicSubject.PHILOSOPHY)));
        university.getFaculties().get(1).getGroup("L001").setStudent(new Student("Pavel", "Petrov",
                EnumSet.of(AcademicSubject.ENGLISH_LITERATURE, AcademicSubject.ENGLISH, AcademicSubject.GERMAN, AcademicSubject.PHILOSOPHY)));
        university.getFaculties().get(1).getGroup("L002").setStudent(new Student("Oleg", "Olegov",
                EnumSet.of(AcademicSubject.ENGLISH_LITERATURE, AcademicSubject.ENGLISH, AcademicSubject.GERMAN, AcademicSubject.PHONETICS)));
        university.getFaculties().get(1).getGroup("L002").setStudent(new Student("Petr", "Olegov",
                EnumSet.of(AcademicSubject.ENGLISH_LITERATURE, AcademicSubject.ENGLISH, AcademicSubject.GERMAN, AcademicSubject.PHONETICS)));

        university.getFaculties().get(2).getGroup("M001").setStudent(new Student("Maksim", "Petrov",
                EnumSet.of(AcademicSubject.GEOMETRY, AcademicSubject.ENGLISH, AcademicSubject.TRIGONOMETRY, AcademicSubject.ALGEBRA, AcademicSubject.PHILOSOPHY)));
        university.getFaculties().get(2).getGroup("M002").setStudent(new Student("Oleg", "Maksimov",
                EnumSet.of(AcademicSubject.GEOMETRY, AcademicSubject.ENGLISH, AcademicSubject.TRIGONOMETRY, AcademicSubject.ALGEBRA, AcademicSubject.PHILOSOPHY)));
        university.getFaculties().get(2).getGroup("M002").setStudent(new Student("Maksim", "Olegov",
                EnumSet.of(AcademicSubject.GEOMETRY, AcademicSubject.TRIGONOMETRY, AcademicSubject.ALGEBRA, AcademicSubject.PHILOSOPHY)));
    }

    @Override
    protected void setGrades() throws GradeOutOfRangeException, StudentWithoutAcademicSubjectsException,
            GroupWithoutStudentsException, FacultyWithoutGroupsException, UniversityWithoutFacultiesException {
        for (Faculty faculty : university.getFaculties()) {
            for (Group group : faculty.getGroups()) {
                for (Student student : group.getStudents()) {
                    for (AcademicSubject studentSubject : student.getAcademicSubjects()) {
                        int gradesCount = (int) (Math.random() * 4);
                        for (int i = 0; i < gradesCount; i++) {
                            student.setGrade(studentSubject, (int) ((Math.random() * 10) + 1));
                        }
                    }
                }
            }
        }
    }
}
