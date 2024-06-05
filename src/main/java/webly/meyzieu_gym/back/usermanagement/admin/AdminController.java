package webly.meyzieu_gym.back.usermanagement.admin;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public List<UserProfileForAdminDto> getAllUsers() {
        return adminService.getUsersForAdmin();
    }

    @GetMapping("/users/{id}")
    public UserProfileForAdminDto getUserProfile(@PathVariable Long id) {
        return adminService.getUserById(id);
    }
}
