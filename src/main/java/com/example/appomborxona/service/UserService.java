package com.example.appomborxona.service;

import com.example.appomborxona.entity.User;
import com.example.appomborxona.entity.Warehouse;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.payload.UserDto;
import com.example.appomborxona.repository.UserRepository;
import com.example.appomborxona.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result add(UserDto userDto) {

        // Check phone number exists
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists)
            return new Result("Phone number already exists", false);

        // Add user
        List<Warehouse> warehouseList = new ArrayList<>();
        List<Integer> warehouseIdList = userDto.getWarehouseId();
        for (Integer id : warehouseIdList) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
            optionalWarehouse.ifPresent(warehouseList::add);
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setWarehouse(warehouseList);
        userRepository.save(user);
        return new Result("User added", true);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    public Result edit(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {

            Set<Warehouse> warehouseList = new HashSet<>();
            List<Integer> warehouse = userDto.getWarehouseId();

            for (Integer i : warehouse) {
                warehouseList.add(warehouseRepository.findById(i).get());
            }

            User user = optionalUser.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
//            user.setWarehouse(warehouseList);
            userRepository.save(user);
            return new Result("User edited", true);
        }
        return new Result("User not found", false);
    }

    public Result delete(Integer id) {
        userRepository.deleteById(id);
        return new Result("User deleted", true);
    }
}
