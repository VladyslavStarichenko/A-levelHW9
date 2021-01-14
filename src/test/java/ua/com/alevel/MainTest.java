package ua.com.alevel;


import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;
import ua.com.alevel.db.impl.ProfileDBImpl;
import ua.com.alevel.entity.Profile;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.alevel.util.ProfileUtilTest.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTest implements AbstractCrudTest{

   @BeforeAll
   public static void init() {
        for (int i = 0; i < COLLECTION_SIZE; i++) {
            int age = 21;
            int salary = 1500;
            String name = "testName" + i;
            String job = "job" + i ;
            Profile profile = new Profile();
            profile.setAge(age);
            profile.setName(name);
            profile.setJob(job);
            profile.setSalary(salary);
            ProfileDBImpl.getInstance().create(profile);
        }
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), COLLECTION_SIZE);



    }
    @AfterAll
    public static void deleteAll(){
        List<Integer> profileIdList = ProfileDBImpl.getInstance().readAll().stream().map(Profile::getId).collect(Collectors.toList());
        Assert.assertTrue(CollectionUtils.isNotEmpty(profileIdList));
        for (Integer id : profileIdList) {
            ProfileDBImpl.getInstance().delete(id);
        }
        Assert.assertTrue(CollectionUtils.isEmpty(ProfileDBImpl.getInstance().readAll()));
    }
    @Test
    @Order(2)
    @Override
    public void create() {
        Profile Vlad = new Profile();
        Vlad.setAge(CREATE_AGE);
        Vlad.setName(CREATE_NAME);
        Vlad.setJob(CREATE_JOB);
        Vlad.setSalary(CREATE_SALARY);
        ProfileDBImpl.getInstance().create(Vlad);
        List<Profile> profiles=  ProfileDBImpl.getInstance().readAll();
        Assert.assertEquals(profiles.size(),1);
    }
    @Test
    @Order(1)
    @Override
    public void read() {
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertEquals(profiles.size(), 0);
    }
    @Test
    @Order(3)
    @Override
    public void update() {
        List<Profile> profiles = ProfileDBImpl.getInstance().read(FIELD_NAME, CREATE_NAME);

        Assert.assertEquals(profiles.size(), 1);
        Profile profile = profiles.get(0);
        profile.setName(UPDATE_NAME);
        ProfileDBImpl.getInstance().update(profile);
        profiles = ProfileDBImpl.getInstance().read(FIELD_NAME, UPDATE_NAME);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 0);
    }
    @Test
    @Order(4)
    @Override
    public void delete() {
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertEquals(profiles.size(), 1);
        ProfileDBImpl.getInstance().delete(profiles.get(0).getId());

    }





}
