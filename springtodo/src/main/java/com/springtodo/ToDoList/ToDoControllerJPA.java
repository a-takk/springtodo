package com.springtodo.ToDoList;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ToDoControllerJPA {
    private ToDoRepository toDoRepository;

    private ToDoControllerJPA(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @RequestMapping("list-ToDos")
    public String listToDos(ModelMap model) {
        String username = getUsername(model);
        List<ToDo> toDos = toDoRepository.findByUsername(username);
        model.addAttribute("toDos", toDos);
        return "listToDos";
    }

    private static String getUsername(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String addToDo(ModelMap model) {
        String username = getUsername(model);
        ToDo todo = new ToDo(0,username,"",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "addToDos";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "addToDos";
        }
        String username = getUsername(model);
        todo.setUsername(username);
        toDoRepository.save(todo);
        return "redirect:list-ToDos";
    }
    @RequestMapping("delete-todo")
    public String delete(@RequestParam int id) {
        toDoRepository.deleteById(id);
        return "redirect:list-ToDos";
    }
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showupdate(@RequestParam int id, ModelMap model) {
        ToDo todo = toDoRepository.findById(id).get();
        model.addAttribute("todo",todo);
        return "addToDos";
    }
    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String update(ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "addToDos";
        }
        String username = getUsername(model);
        todo.setUsername(username);
        toDoRepository.save(todo);
        return "redirect:list-ToDos";
    }

}
