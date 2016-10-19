package org.talem.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.talem.messenger.database.Database;
import org.talem.messenger.model.Profile;

public class ProfileService {
private Map<String, Profile> profiles = Database.getProfiles();
	
	public ProfileService(){
		profiles.put("Mayur1", new Profile(1L, "myProfile1", "Mayur", "Tale"));
		profiles.put("Mayur2", new Profile(2L, "myProfile12", "Mayur", "Deshmukh"));
	}
	
	public List<Profile> getAllMessages(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	 public Profile updateProfile(Profile profile){
		 if(profile.getProfileName().isEmpty()){
			 return null;
		 }
		 profiles.put(profile.getProfileName(), profile);
		 return profile;
	 }
	public Profile removeProfile(String profileName){
		if(profiles.get(profileName).equals(null)){
			return null;
		}
		return profiles.remove(profileName);
	}

}
