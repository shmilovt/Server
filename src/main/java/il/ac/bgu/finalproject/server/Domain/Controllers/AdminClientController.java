package il.ac.bgu.finalproject.server.Domain.Controllers;

import il.ac.bgu.finalproject.server.Domain.Exceptions.NoUserNameException;

public class AdminClientController {

    public boolean login (String username, String password) throws NoUserNameException {
        String existUsername = "tamir";
        if(username == null || username.compareTo(existUsername) !=0)
            throw new NoUserNameException(username);
      return true;
    }
}
