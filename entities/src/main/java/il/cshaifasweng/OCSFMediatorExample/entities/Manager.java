package il.cshaifasweng.OCSFMediatorExample.entities;

public class Manager extends Person{
    boolean ApproveTimeToExam;

    public boolean isApproveTimeToExam() {
        return ApproveTimeToExam;
    }

    public void setApproveTimeToExam(boolean approveTimeToExam) {
        ApproveTimeToExam = approveTimeToExam;
    }

    public Manager(String firstName, String lastName, String userName, String passWord, boolean approveTimeToExam) {
        super(firstName, lastName, userName, passWord);
        ApproveTimeToExam = approveTimeToExam;
    }

    public Manager(boolean approveTimeToExam) {
        ApproveTimeToExam = approveTimeToExam;
    }
}
