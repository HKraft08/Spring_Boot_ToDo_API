package org.example.spring_boot_layered_todo.repository;

import org.example.spring_boot_layered_todo.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    
}
