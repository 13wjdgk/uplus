package com.example.springboothtmluseraddress.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboothtmluseraddress.user.dto.UserAddressDto;
import com.example.springboothtmluseraddress.user.dto.UserDto;
import com.example.springboothtmluseraddress.user.dto.UserResultDto;
import com.example.springboothtmluseraddress.user.entity.User;
import com.example.springboothtmluseraddress.user.entity.UserAddress;
import com.example.springboothtmluseraddress.user.entity.UserRole;
import com.example.springboothtmluseraddress.user.repository.UserAddressRepository;
import com.example.springboothtmluseraddress.user.repository.UserRepository;
import com.example.springboothtmluseraddress.user.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final UserAddressRepository userAddressRepository;
	@Override
	public UserResultDto login(String email, String password) {
		UserResultDto userResultDto = new UserResultDto();
		User user = userRepository.findByEmail(email);
		if(user != null && user.getPassword().equals(password)) {
			userResultDto.setResult("success");
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			Map<Integer,String> userRoles = userDto.getRoles();
			for(UserRole userRole : user.getRoles()) {
				userRoles.put((int)userRole.getId(), userRole.getName());
			}
			userResultDto.setUserDto(userDto);
			return userResultDto;
		}
		userResultDto.setResult("fail");
		return userResultDto;
	}

	@Override
	public UserResultDto insertUser(User user) {
		UserResultDto userResultDto = new UserResultDto();

		try{
			UserRole userRole = userRoleRepository.findByName("role_customer");
			user.getRoles().add(userRole);
			userRepository.save(user);
			userResultDto.setResult("success");

		}catch (Exception e) {
			userResultDto.setResult("fail");
		}


		return userResultDto;
	}

	@Override
	public UserResultDto detailUser(long id) {
		UserResultDto userResultDto = new UserResultDto();
		Optional<User> user = userRepository.findById(id);
		user.ifPresentOrElse(u ->{
			UserDto userDto = new UserDto();
			userDto.setId(u.getId());
			userDto.setName(u.getName());
			userDto.setEmail(u.getEmail());
			userDto.setPassword(u.getPassword());
			userResultDto.setResult("success");
			userResultDto.setUserDto(userDto);
		}, () -> {
			userResultDto.setResult("fail");
		});

		return userResultDto;
	}

	@Override
	public UserResultDto listUserAddress(long id) {
		UserResultDto userResultDto = new UserResultDto();
		//List<UserAddress> userAddresses = userAddressRepository.findByUserId(id);
		List<UserAddress> userAddresses = userAddressRepository.findAll();

		System.out.println("size"+userAddresses.size());
		List<UserAddressDto> addresses = new ArrayList<>();
		userAddresses.forEach(ua -> {
			UserAddressDto userAddressDto = new UserAddressDto();
			userAddressDto.setId(ua.getId());
			userAddressDto.setAddr1(ua.getAddr1());
			userAddressDto.setAddr2(ua.getAddr2());
			userAddressDto.setZipCode(ua.getZipCode());
			addresses.add(userAddressDto);


		});

		UserDto userDto = new UserDto();
		userDto.setId(id);
		userDto.setAddresses(addresses);
		userResultDto.setUserDto(userDto);
		userResultDto.setResult("success");

		return userResultDto;
	}

	//UserAddress를 등록할 때 JPA의 연관관계를 이용해서 등록
	//만약 User와 연관관계가 없다면 UserAddress를 등록할 때 User의 id를 받아서 User를 찾아서 연관관계를 설정해야 한다.
	@Override
	public UserResultDto insertUserAddress(UserAddress userAddress,long id) {
		UserResultDto userResultDto = new UserResultDto();
		userAddress.setUserId(id);
		userAddressRepository.save(userAddress);
		userResultDto.setResult("success");
		return userResultDto;
		//#3
//		Optional<User> user = userRepository.findById(id);
//		user.ifPresentOrElse(u ->{
//			userAddress.setUser(u);
//			try{
//				userAddressRepository.save(userAddress);
//				List<UserAddress> userAddresses = userAddressRepository.findByUserId(id);
//				UserDto userDto = new UserDto();
//				List<UserAddressDto> addresses = userDto.getAddresses();
//				userAddresses.forEach(ua -> {
//					UserAddressDto userAddressDto = new UserAddressDto();
//					userAddressDto.setId(ua.getId());
//					userAddressDto.setAddr1(ua.getAddr1());
//					userAddressDto.setAddr2(ua.getAddr2());
//					userAddressDto.setZipCode(ua.getZipCode());
//					addresses.add(userAddressDto);
//
//
//				});
//				userDto.setId(id);
//				userResultDto.setUserDto(userDto);
//				userResultDto.setResult("success");
//
//			}catch (Exception e) {
//				userResultDto.setResult("fail");
//			}
//
//		}, () -> {
//			userResultDto.setResult("fail");
//		});
//		return userResultDto;
	}
}
