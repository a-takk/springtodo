package com.springtodo.ToDoList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    public List<ToDo> findByUsername(String username);

}
