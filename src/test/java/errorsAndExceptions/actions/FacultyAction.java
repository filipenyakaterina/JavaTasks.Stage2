package errorsAndExceptions.actions;

import errorsAndExceptions.universityEntities.AcademicSubject;
import errorsAndExceptions.universityEntities.Faculty;
import errorsAndExceptions.universityEntities.Group;
import errorsAndExceptions.universityEntities.Student;
import errorsAndExceptions.universityExceptions.FacultyWithoutGroupsException;
import errorsAndExceptions.universityExceptions.GroupWithoutStudentsException;

import java.util.ArrayList;
import java.util.List;

public class FacultyAction extends AverageCalculator {

    public static boolean hasGroup(Faculty faculty, String groupName) throws FacultyWithoutGroupsException {
        return faculty.getGroups().stream().anyMatch(group -> group.getGroupName().equals(groupName));
    }

    public static List<Student> getStudentsOfFaculty(Faculty faculty) throws FacultyWithoutGroupsException, GroupWithoutStudentsException {
        ArrayList<Student> students = new ArrayList<>();
        for (Group group : faculty.getGroups()) {
            students.addAll(group.getStudents());
        }
        return students;
    }

    public static Double getAverageGradeBySubject(Faculty faculty, AcademicSubject subject) throws GroupWithoutStudentsException,
            FacultyWithoutGroupsException {
        return calculateAverageGrade(getStudentsOfFaculty(faculty), subject);
    }
}
