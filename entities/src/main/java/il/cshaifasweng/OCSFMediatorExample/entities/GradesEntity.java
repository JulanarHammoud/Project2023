package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.Arrays;
import java.util.List;

public class GradesEntity {
int id;
List<ExamStudent> ExamsOfStudents;
double average;
int median;
int distribution;
int distribution1;
int[] arrayhelp=new int[11];
int[] arrayhelp1=new int[101];
    public GradesEntity(List<ExamStudent> examsOfStudents) {
        ExamsOfStudents = examsOfStudents;
    }
    public void setExamsOfStudents(List<ExamStudent> examsOfStudents) {
        ExamsOfStudents = examsOfStudents;
    }

    public List<ExamStudent> getExamsOfStudents() {
        return ExamsOfStudents;
    }

    public double getAverage() {
        int gradetotal=0,i=0;
        for(ExamStudent examStudent:ExamsOfStudents){
            if(examStudent.isApprove()) {
                gradetotal=gradetotal+examStudent.getGrade();
                i++;
            }
        }
        if(i==0){
            this.average=0;
        }else{
            double avg = gradetotal/i;
            this.average = avg;
        }
        return average;
    }

    public int getMedian() {
        int i=0;
        int[] array = new int[ExamsOfStudents.size()];

        for(ExamStudent examStudent:ExamsOfStudents){
            if(examStudent.isApprove()) {
                array[i]=examStudent.grade;
                i++;
            }
        }
        // Sort the array in ascending order
        Arrays.sort(array);
        int middle = (i-1) / 2;
        int med;
        if(i>1){
            if ((i-1) % 2 == 0) {
                // If there's an even number of elements, calculate the average of the two middle values
                med = (array[middle - 1] + array[middle]) / 2;
            } else {
                // If there's an odd number of elements, the median is the middle value
                med = array[middle];
            }
        }else{
            med=array[0];
        }
        this.median = med;
        return median;
    }
public void setDistribution(int Grade) {
    for(int i=0; i<11; i++){
        arrayhelp[i]=0;
    }
    for(ExamStudent examStudent:ExamsOfStudents){
        if(examStudent.isApprove()) {
            if(examStudent.grade==0){
                arrayhelp[0]++;
            }
            arrayhelp[(examStudent.grade-1)/10]++;
        }
    }
    this.distribution = arrayhelp[Grade/10];
}

    public int getDistribution(int Grade) {
        return arrayhelp[Grade];
    }

    public int getDistribution1(int Grade) {
        return arrayhelp1[Grade];
    }

    public void setDistribution1(int Grade) {
        for(int i=0; i<101; i++){
            arrayhelp1[i]=0;
        }
        for(ExamStudent examStudent:ExamsOfStudents){
            if(examStudent.isApprove()) {
                arrayhelp1[examStudent.grade]++;
            }
        }
        this.distribution1 = arrayhelp1[Grade];
    }
}

