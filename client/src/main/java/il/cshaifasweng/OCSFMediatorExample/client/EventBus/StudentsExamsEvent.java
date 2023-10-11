package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.StudentsExams;

public class StudentsExamsEvent {
    StudentsExams studentsExams;

    public StudentsExamsEvent(StudentsExams studentsExams) {
        this.studentsExams = studentsExams;
    }

    public StudentsExams getStudentsExams() {
        return studentsExams;
    }

    public void setStudentsExams(StudentsExams studentsExams) {
        this.studentsExams = studentsExams;
    }
}
