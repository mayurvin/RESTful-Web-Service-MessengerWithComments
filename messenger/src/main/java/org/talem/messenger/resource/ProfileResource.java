package org.talem.messenger.resource;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.talem.messenger.model.Message;
import org.talem.messenger.model.Profile;
import org.talem.messenger.service.ProfileService;

@Path("profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	private ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return profileService.getAllMessages();
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return profileService.addProfile(profile);

	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName")String profileName, Profile profile){
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public Profile deleteProfile(@PathParam("profileName")String profileName){
		return profileService.removeProfile(profileName);
	}
	
	@GET
	@Path("/{profileName}")
	public Profile test(@PathParam("profileName")String profileName){
		
		return profileService.getProfile(profileName);
	}
	
}
