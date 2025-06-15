package dev.mordechai.studentcoursemanager.repository;

import dev.mordechai.studentcoursemanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
