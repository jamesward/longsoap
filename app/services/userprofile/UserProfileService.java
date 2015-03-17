package services.userprofile;

import models.UserProfile;

import javax.jws.WebService;

@WebService
public interface UserProfileService {

    public UserProfile get();

}
