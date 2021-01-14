package ua.com.alevel.db.impl;

import ua.com.alevel.db.ProfileDB;
import ua.com.alevel.entity.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileDBImpl implements ProfileDB {

    private final List<Profile> profiles;

    private static ProfileDBImpl instance;

    private ProfileDBImpl() {
        profiles = new ArrayList<>();
    }

    public static ProfileDBImpl getInstance() {
        if (instance == null) {
            instance = new ProfileDBImpl();
        }
        return instance;
    }

    @Override
    public void create(Profile profile) {
        int size = profiles.size();
        ++size;
        profile.setId(size);
        profiles.add(profile);
    }

    @Override
    public List<Profile> read(String fieldName, Object value) {
        return null;
    }

    @Override
    public List<Profile> readAll() {
        return profiles;
    }

    @Override
    public void update(Profile profile) {
        List<Profile> users = read("id", profile.getId());
        Profile currentProfile= users.get(0);
        currentProfile.setAge(profile.getAge());
        currentProfile.setName(profile.getName());
        currentProfile.setSalary(profile.getSalary());
        currentProfile.setJob(profile.getJob());
    }

    @Override
    public void delete(Integer id) {
        profiles.removeIf(profile ->  profile.getId().equals(id));
    }
}
