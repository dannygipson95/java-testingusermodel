package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserById() {
        long userId = userService.findByName("cinnamon").getUserid();
        assertEquals("cinnamon", userService.findUserById(userId).getUsername());

    }

    @Test
    public void findByNameContaining() {
        assertEquals(2, userService.findByNameContaining("in").size());
    }

    @Test
    public void findAll() {
        assertEquals(5, userService.findAll().size());
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByName() {
        assertEquals("1234567", userService.findByName("cinnamon").getPassword());
    }

    @Test
    public void save() {
        User testUser = new User("testUser",
                "testPassword",
                "testEmail@lambdaschool.local");
        testUser.getRoles().add(new UserRoles(testUser, roleService.findAll().get(0)));
        testUser.getUseremails()
                .add(new Useremail(testUser,
                        "testEmail2@email.local"));
        userService.save(testUser);

        assertNotNull(userService.findByName("testUser"));
    }

    @Test
    public void update() {
        User updateUser = new User();
        updateUser.setPassword("newPassord");
        userService.update(updateUser, userService.findByName("cinnamon").getUserid());
    }

    @Test
    public void deleteAll() {
    }
}