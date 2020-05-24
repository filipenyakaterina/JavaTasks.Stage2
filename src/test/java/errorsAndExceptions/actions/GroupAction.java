package errorsAndExceptions.actions;

import errorsAndExceptions.universityEntities.AcademicSubject;
import errorsAndExceptions.universityEntities.Group;
import errorsAndExceptions.universityExceptions.GroupWithoutStudentsException;

public class GroupAction extends AverageCalculator {
    public static Double getAverageGradeBySubject(Group group, AcademicSubject subject)
            throws GroupWithoutStudentsException {
        return calculateAverageGrade(group.getStudents(), subject);
    }
}
