package com.indusnet.ums.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.model.UserModel;

import jakarta.validation.Valid;

public interface IUserService {

	ResponseModel add(@Valid UserModel model);

	List<UserModel> getUsers();

	UserModel getUserById(String id);

	void deleteUser(String id);

	ResponseModel updateUser(UserModel user);
}
