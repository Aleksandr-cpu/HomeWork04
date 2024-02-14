package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;
import notebook.util.UserValidator;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        return repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public List<User> readAll() {
        return repository.findAll();
    }

    public void deleteUser(String userId) {
        boolean isUser = repository.delete(Long.parseLong(userId));
        if (isUser){
            System.out.println("пользователь удален");
        }else {
            System.out.println("пользователь не найден");
        }
    }
    public User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");

        UserValidator validator = new UserValidator();


        return validator.validate(new User(firstName, lastName, phone));
    }

    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

}
