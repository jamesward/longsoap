package services.userprofile;

import models.UserProfile;

import javax.jws.WebService;

@WebService(endpointInterface = "services.userprofile.UserProfileService")
public class UserProfileServiceImpl implements UserProfileService {

    @Override
    public UserProfile get() {

        UserProfile userProfile = new UserProfile();

        userProfile.setName("John Doe");
        userProfile.setRole("ADMIN");
        userProfile.setEmail("john@doe.net");

        return userProfile;
    }

}
