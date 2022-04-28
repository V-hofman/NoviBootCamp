package novi.bootcamp.schoolproject.exceptions;

public class TeacherNotFoundException extends Exception {


    public TeacherNotFoundException(String errorMessage)
    {
        super(errorMessage);
    }

    public TeacherNotFoundException(String errorMessage, Throwable err)
    {
        super(errorMessage, err);
    }

}
