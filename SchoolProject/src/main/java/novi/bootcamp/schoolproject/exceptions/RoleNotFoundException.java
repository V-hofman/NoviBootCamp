package novi.bootcamp.schoolproject.exceptions;

import net.bytebuddy.implementation.bytecode.Throw;

public class RoleNotFoundException extends Exception{

    public RoleNotFoundException(String errorMessage)
    {
        super(errorMessage);
    }

    public RoleNotFoundException(String errorMessage, Throwable err)
    {
        super(errorMessage, err);
    }
}
