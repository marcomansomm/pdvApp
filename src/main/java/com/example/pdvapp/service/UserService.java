package com.example.pdvapp.service;

import com.example.pdvapp.dto.UserDTO;
import com.example.pdvapp.entity.User;
import com.example.pdvapp.exception.NoItemException;
import com.example.pdvapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map( user -> {


            return new UserDTO(user.getId(), user.getName(), user.isEnable());

        }).collect(Collectors.toList());

    }

    public UserDTO save(User user) {
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.isEnable());
    }

    public UserDTO findById(long id) {
        Optional<User> optional = userRepository.findById(id);

        if(optional.isEmpty()){
            throw new NoItemException("Usuario não encontrado!!");
        }
        User user = optional.get();



        return new UserDTO(user.getId(), user.getName(), user.isEnable());
    }

    public UserDTO update(User user){
        Optional<User> userToEdit = userRepository.findById(user.getId());

        if(userToEdit.isEmpty()){
            throw new NoItemException("Usuario não encontrado!!");
        }
        userRepository.save(user);

        return new UserDTO(user.getId(), user.getName(), user.isEnable());
    }

    public void deleteById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoItemException("Usuario a ser deletado não existe"));
        userRepository.deleteById(id);
    }
}
