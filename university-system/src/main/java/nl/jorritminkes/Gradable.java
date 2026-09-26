package nl.jorritminkes;

import java.util.Map;

public interface Gradable {
    void submitAssignment(String assignmentName, String submission);
    double getAssignmentScore(String assignmentName);
    Map<String, String> getAllAssignments();
}