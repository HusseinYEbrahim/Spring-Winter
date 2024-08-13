package com.sumerge.courses.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sumerge.courses.models.Admin;
import com.sumerge.courses.repositories.AdminRepository;

@Component
public class Intializations implements ApplicationRunner {

    @Value("${app.admin.username}")
	String adminUserName;

	@Value("${app.admin.password}")
	String adminPassword;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        createAdminIfNotExists();
    }

    public void createAdminIfNotExists()
    {
        Admin admin = Admin.builder()
						.username(adminUserName)
                        .password(adminPassword)
                        .build();
        try{
            adminRepository.save(admin);
        }
        catch(Exception ex)
        {
            return;
        }
    }

    
    
}
