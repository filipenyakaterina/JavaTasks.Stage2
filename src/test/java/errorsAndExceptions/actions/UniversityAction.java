package errorsAndExceptions.actions;

import errorsAndExceptions.universityEntities.*;
import errorsAndExceptions.universityExceptions.FacultyWithoutGroupsException;
import errorsAndExceptions.universityExceptions.GroupWithoutStudentsException;
import errorsAndExceptions.universityExceptions.NoFacultyWithNameException;
import errorsAndExceptions.universityExceptions.UniversityWithoutFacultiesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniversityAction extends AverageCalculator {
    public static Double getAverageGradeOfFaculty(University university, String facultyName, AcademicSubject subject)
            throws FacultyWithoutGroupsException, GroupWithoutStudentsException, UniversityWithoutFacultiesException, NoFacultyWithNameException {
        Faculty facultyByName;
        Optional<Faculty> optionalFaculty = university.getFaculties().stream().
                filter(faculty -> faculty.getFacultyName().equals(facultyName)).findFirst();
        if (optionalFaculty.isPresent()) {
            facultyByName = optionalFaculty.get();
        } else {
            throw new NoFacultyWithNameException("В университете отсутствует факультет с именем " + facultyName);
        }
        return FacultyAction.getAverageGradeBySubject(facultyByName, subject);
    }

    private static Group searchGroupByName(University university, String groupName) throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException {
        for (Faculty faculty : university.getFaculties()) {
            if (FacultyAction.hasGroup(faculty, groupName)) {
                return faculty.getGroup(groupName);
            }
        }
        return null;
    }

    public static Double getAverageGradeOfGroup(University university, String groupName, AcademicSubject subject)
            throws GroupWithoutStudentsException,
            UniversityWithoutFacultiesException, FacultyWithoutGroupsException {
        Group group;
        Double averageGrade = 0.0;

        if ((group = searchGroupByName(university, groupName)) != null) {
            averageGrade = GroupAction.getAverageGradeBySubject(group, subject);
        }
        return averageGrade;
    }

    public static List<Student> getStudentsOfUniversity(University university) throws FacultyWithoutGroupsException,
            GroupWithoutStudentsException, UniversityWithoutFacultiesException {
        ArrayList<Student> students = new ArrayList<>();
        for (Faculty faculty : university.getFaculties()) {
            students.addAll(FacultyAction.getStudentsOfFaculty(faculty));
        }
        return students;
    }

    public static Double getAverageGradeOfUniversity(University university, AcademicSubject subject)
            throws GroupWithoutStudentsException, FacultyWithoutGroupsException, UniversityWithoutFacultiesException {
        return calculateAverageGrade(getStudentsOfUniversity(university), subject);
    }
}
