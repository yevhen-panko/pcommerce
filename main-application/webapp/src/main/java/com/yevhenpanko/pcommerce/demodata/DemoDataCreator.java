package com.yevhenpanko.pcommerce.demodata;

import com.yevhenpanko.pcommerce.entity.user.Permission;
import com.yevhenpanko.pcommerce.entity.user.UserRole;
import com.yevhenpanko.pcommerce.repository.UserRoleRepository;
import com.yevhenpanko.pcommerce.service.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:/application.properties")
public class DemoDataCreator implements ApplicationListener<ContextRefreshedEvent> {
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private final UserManagement userManagement;
    private final UserRoleRepository userRoleRepository;

    @Value("${create.demo.data}")
    private boolean createDemoData;

    @Value("${admin.email}")
    private String adminEmail;

    @Autowired
    public DemoDataCreator(UserManagement userManagement, UserRoleRepository userRoleRepository) {
        this.userManagement = userManagement;
        this.userRoleRepository = userRoleRepository;
    }

    public void createDemoData() {
        if (createDemoData && !adminUserExists() && !adminRoleExists()) {
            final UserRole adminRole = userRoleRepository.save(new UserRole(ADMIN_ROLE, Permission.values()));
            final long admin = userManagement.create(
                    adminEmail,
                    "admin",
                    "John"
                    , "Doe",
                    adminRole
            );
        }
    }

    private boolean adminUserExists() {
        return userManagement.readByEmail(adminEmail).isPresent();
    }

    private boolean adminRoleExists() {
        return userRoleRepository.findByName(ADMIN_ROLE).isPresent();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createDemoData();
    }
}
