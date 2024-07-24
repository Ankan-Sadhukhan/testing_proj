package com.indusnet.ums.service.impl;

import java.time.Instant;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.exception.CustomNotFoundException;
import com.indusnet.ums.exception.UnprocessableException;
import com.indusnet.ums.model.UserModel;
import com.indusnet.ums.repository.IUserServiceRepository;
import com.indusnet.ums.service.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

	@Autowired
	Gson gson = new Gson();

	@Autowired
	IUserServiceRepository repository;

	@Override
	public ResponseModel add(UserModel model) {

		ResponseModel responseObj = null;

		Long currentTimeInMilli = Instant.now().toEpochMilli();
		UserModel saveModel = UserModel.builder()
				.email(model.getEmail())
				.name(model.getName())
				.password(model.getName())
				.phone(model.getPhone())
				.dateCreated(currentTimeInMilli)
				.build();

		repository.save(saveModel);

		log.info(gson.toJson(saveModel));

		responseObj = ResponseModel.builder()
				.messageEn("User Added Successfully")
				.messageFr("User Added Successfully")
				.messageTypeId(1)
				.statusCode(HttpStatus.OK)
				.build();

		return responseObj;

	}

	@Override
	public List<UserModel> getUsers() {
		// Optional<UserModel> optionalUser = repository.findById(userId);
		return repository.findAll();
	}

	@Override
	public UserModel getUserById(String userId) {
		return repository.findById(userId).orElse(null);
	}

	@Override
	public void deleteUser(String userId) {
		repository.deleteById(userId);

	}

	@Override
	public ResponseModel updateUser(UserModel usr) {
		UserModel user = repository.findById(usr.getId()).orElse(null);
		user.setEmail(usr.getEmail());
		user.setName(usr.getName());
		user.setPassword(usr.getPassword());
		repository.save(user);
		return ResponseModel.builder()
				.messageEn("User updated successfully")
				.messageFr("user updated succesfully")
				.messageTypeId(1)
				.statusCode(HttpStatus.OK)
				.build();

	}

}
