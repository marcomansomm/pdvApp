package com.example.pdvapp.service;

import com.example.pdvapp.dto.UserDTO;
import com.example.pdvapp.dto.UserResponseDTO;
import com.example.pdvapp.entity.User;
import com.example.pdvapp.exception.NoItemException;
import com.example.pdvapp.repository.UserRepository;
import com.example.pdvapp.security.SecurityConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private ModelMapper mapper;

    public UserService(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream().map( user -> {


            return new UserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.isEnable());

        }).collect(Collectors.toList());

    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setPassword(SecurityConfig.passwordEncoder().encode(userDTO.getPassword()));
        User userToSave = mapper.map(userDTO, User.class);
        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getName(), userToSave.getUsername(), userToSave.getPassword(), userToSave.isEnable());
    }

    public UserDTO findById(long id) {
        Optional<User> optional = userRepository.findById(id);

        if(optional.isEmpty()){
            throw new NoItemException("Usuario não encontrado!!");
        }
        User user = optional.get();



        return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.isEnable());
    }

    public UserDTO update(UserDTO userDTO){
        User userToSave = mapper.map(userDTO, User.class);
        Optional<User> userToEdit = userRepository.findById(userToSave.getId());

        if(userToEdit.isEmpty()){
            throw new NoItemException("Usuario não encontrado!!");
        }

        userRepository.save(userToSave);

        return new UserDTO(userToSave.getId(), userToSave.getName(), userToSave.getUsername(), userToSave.getPassword(), userToSave.isEnable());
    }

    public void deleteById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoItemException("Usuario a ser deletado não existe"));
        userRepository.deleteById(id);
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
