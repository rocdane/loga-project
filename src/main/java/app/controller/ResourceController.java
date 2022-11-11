package app.controller;

import app.model.Profile;
import app.service.manager.IManageResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/resource")
public class ResourceController {

    IManageResource manageResource;

    @PostMapping("/profile")
    Profile enregistrerProfile(@RequestBody Profile profile) {
        return manageResource.createProfile(profile);
    }

    @GetMapping("/profiles")
    List<Profile> listProfile() {
        return manageResource.listProfile();
    }

    @GetMapping("/profiles/{txt}")
    List<Profile> searchProfile(@PathVariable String txt) {
        return null;
    }

    @GetMapping("/profile/{id}")
    Profile showProfile(@PathVariable long id) {
        return manageResource.findProfile(id);
    }

    @GetMapping("/profile/{name}")
    Profile showProfile(String name) {
        return manageResource.findProfile(name);
    }

    @PutMapping("/profile/{id}")
    void editProfile(@RequestBody Profile up, @PathVariable Long id) {
        manageResource.editProfile(id);
    }

    @DeleteMapping("/profile/{id}")
    void deleteProfile(@PathVariable Long id) {
        manageResource.deleteProfile(id);
    }
}
