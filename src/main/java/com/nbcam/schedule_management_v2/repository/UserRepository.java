package com.nbcam.schedule_management_v2.repository;

import com.nbcam.schedule_management_v2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
