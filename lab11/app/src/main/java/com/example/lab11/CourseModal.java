package com.example.lab11;
public class CourseModal {

    // variables for our coursename,
    // description, tracks and duration, id.
    private String courseName;
    private String courseDescription;
    private String selectedDate;
    private int id;

    // creating getter and setter methods
    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseDescription()
    {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription)
    {
        this.courseDescription = courseDescription;
    }

    public String getselectedDate()
    {
        return selectedDate;
    }

    public void setselectedDate(String selectedDate)
    {
        this.selectedDate = selectedDate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    // constructor
    public CourseModal(String courseName,
                       String courseDescription,
                       String selectedDate)
    {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.selectedDate = selectedDate;

    }
}

