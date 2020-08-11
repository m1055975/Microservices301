package com.mindtree.userservice.service.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.userservice.cryptografer.PasswordEncryptorDecryptor;
import com.mindtree.userservice.dto.AddBalane;
import com.mindtree.userservice.dto.AddYoYoBalanceDto;
import com.mindtree.userservice.dto.LoginDto;
import com.mindtree.userservice.dto.LoginResponseDto;
import com.mindtree.userservice.dto.UpdateProfileDto;
import com.mindtree.userservice.dto.UpdateResponseDto;
import com.mindtree.userservice.dto.UserDto;
import com.mindtree.userservice.dto.UserRegistrationDto;
import com.mindtree.userservice.dto.UserResponseDto;
import com.mindtree.userservice.entity.User;
import com.mindtree.userservice.exception.InSufficientAmountToBuyYoyoPoints;
import com.mindtree.userservice.exception.InSufficientBalance;
import com.mindtree.userservice.exception.NoSuchUserFound;
import com.mindtree.userservice.exception.PassWordDidNotMatchException;
import com.mindtree.userservice.exception.ServiceException;
import com.mindtree.userservice.repository.UserRepository;
import com.mindtree.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private ModelMapper modelMapper = new ModelMapper();

	Properties properties = new Properties();

	InputStream inputStream = getClass().getClassLoader().getResourceAsStream("error.properties");

	@Override
	public UserRegistrationDto registerUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		UserRegistrationDto registerResponse = new UserRegistrationDto();
		List<String> errorSignUp = new ArrayList<String>();
		List<String> validInputDetails = new ArrayList<String>();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Object> userIdValidation = userIdValidation(userDto.getUserId());
		
		System.out.println("ObjextList------------------->"+userIdValidation);
		
		boolean b = (boolean) userIdValidation.get(0);
		if (b) {
			validInputDetails.add((String) userIdValidation.get(1));
			
		} else {
			errorSignUp.add((String) userIdValidation.get(1));
		}
		// user Name Validation
		List<Object> nameValidation = nameValidation(userDto.getUserName());
		if ((boolean) nameValidation.get(0)) {
			validInputDetails.add((String) nameValidation.get(1));
		} else {
			errorSignUp.add((String) nameValidation.get(1));
		}
		// user Phone Number validation
		List<Object> phoneValidation = phoneNumberValidation(userDto.getPhoneNumber());
		if ((boolean) phoneValidation.get(0)) {
			validInputDetails.add((String) phoneValidation.get(1));
		} else {
			errorSignUp.add((String) phoneValidation.get(1));
		}
		// user email-Id validation
		List<Object> emailValidation = emailIdVerification(userDto.getEmailId());
		if ((boolean) emailValidation.get(0)) {
			validInputDetails.add((String) emailValidation.get(1));
		} else {
			errorSignUp.add((String) emailValidation.get(1));
		}
		// password validation
		List<Object> passwordValidation = passwordValidation(userDto.getPassword());
		
		if((boolean) passwordValidation.get(0))
		{
			errorSignUp.add((String) passwordValidation.get(1));
		}
		// address validation
		List<Object> addrResponse = addressValidation(userDto.getAddress());
		if ((boolean) addrResponse.get(0)) {
			validInputDetails.add((String) addrResponse.get(1));
		} else {
			errorSignUp.add((String) addrResponse.get(1));
		}

		String encryptedPassword = PasswordEncryptorDecryptor.encrypt(user.getPassword());
		user.setPassword(encryptedPassword);
		user.setYoyoBalance(0.0);

		user.setPasswordBycrptor("{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword()));

		System.out.println("ErrorSignUp:"+errorSignUp.size());
		
		if (errorSignUp.size() == 0) {
			//System.err.println(user);
			System.out.println("Error User:"+user);
			userRepository.save(user);
			registerResponse.setMessage("User Registered Successfully..!!!");
			validInputDetails.add("YoYo Balance :" + "0.0");
		} else {
			registerResponse.setMessage("User Registration Failed Check input Credentials");
		}
		registerResponse.setValidInputDetails(validInputDetails);
		if (errorSignUp.size() != 0) {
			registerResponse.setErrorSignUp(errorSignUp);
		} else {
			errorSignUp.add("No Errors . All Input Credentials Are Correct...");
			registerResponse.setErrorSignUp(errorSignUp);
		}
		return registerResponse;
	}

	@Override
	public AddYoYoBalanceDto addYoyoBalance(AddBalane balanceDto, String userId) throws ServiceException {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new NoSuchUserFound();
		}
		AddYoYoBalanceDto balance = modelMapper.map(balanceDto, AddYoYoBalanceDto.class);
		if (balance.getAmountToBuyPoints() < 5) {
			throw new InSufficientAmountToBuyYoyoPoints();
		}
		double points = balance.getAmountToBuyPoints() / 5;
		balance.setPointsPurchased(points);
		user.setYoyoBalance(user.getYoyoBalance() + points);
		user.setUserId(userId);
		userRepository.save(user);
		balance.setUserName(user.getUserName());
		balance.setYoYoBalance(user.getYoyoBalance());
		balance.setMessage("YoYo Balance Updated SuccessFully...!");
		return modelMapper.map(balance, AddYoYoBalanceDto.class);
	}

	@Override
	public UpdateResponseDto updateProfile(UpdateProfileDto updateProfileDto, String userId) throws ServiceException {
		UpdateResponseDto updateDto = new UpdateResponseDto();
		List<String> response = new ArrayList<String>();
		List<String> errors = new ArrayList<String>();

		User user = modelMapper.map(updateProfileDto, User.class);
		User userToUpdate = userRepository.findByUserId(userId);

		if (userToUpdate == null) {
			throw new NoSuchUserFound();
		}
		// userName validation
		if(user.getUserName()!=null) {
		List<Object> nameValidation = nameValidation(user.getUserName());
		if ((boolean) nameValidation.get(0)) {
			response.add((String) nameValidation.get(1));
		} else {
			errors.add((String) nameValidation.get(1));
		}
		}

		// address Validation
		if(user.getAddress()!=null) {
		List<Object> addressValidation = addressValidation(user.getAddress());
		if ((boolean) addressValidation.get(0)) {
			response.add((String) addressValidation.get(1));
		} else {
			errors.add((String) addressValidation.get(1));
		}
		}
		// phoneNumber Validation
		if(user.getPhoneNumber()!=null) {
		List<Object> phoneValidation = phoneNumberValidation(user.getPhoneNumber());
		if ((boolean) phoneValidation.get(0)) {
			response.add((String) phoneValidation.get(1));
		} else {
			errors.add((String) phoneValidation.get(1));
		}
		}
		// email Validation
		if(user.getEmailId()!=null) {
		List<Object> emailValidation = emailIdVerification(user.getEmailId());
		if ((boolean) emailValidation.get(0)) {
			response.add((String) emailValidation.get(1));
		} else {
			errors.add((String) emailValidation.get(1));
		}
		}
		user = userRepository.saveAndFlush(userToUpdate);
		updateDto.setUpdatedResponse(response);
		updateDto.setErrorsInUpdate(errors);

		return updateDto;

	}

	@Override
	public UserResponseDto profileDetails(String userId) throws ServiceException {
		User user = userRepository.findByUserId(userId);
		UserResponseDto profileDetails = new UserResponseDto();
		if (user != null) {
			profileDetails.setUserId(userId);
			profileDetails.setUserName(user.getUserName());
			profileDetails.setAddress(user.getAddress());
			profileDetails.setEmailId(user.getEmailId());
			profileDetails.setPhoneNumber(user.getPhoneNumber());
			profileDetails.setYoyoBalance(user.getYoyoBalance());
		} else
			throw new NoSuchUserFound();
		return profileDetails;
	}

	@Override
	public LoginResponseDto loginUser(LoginDto loginDto) throws ServiceException {

		User user = modelMapper.map(loginDto, User.class);

		User userFetched = userRepository.findByUserId(user.getUserId());
		LoginResponseDto loginResponseDto = new LoginResponseDto();

		if (userFetched==null) {
			throw new NoSuchUserFound();
		}
		String decrytPassword = PasswordEncryptorDecryptor.decrypt(userFetched.getPassword());
		if (decrytPassword.equals(user.getPassword())) {
			System.out.println("User Succesfully Login");
		} else {
			throw new PassWordDidNotMatchException();
		}
		loginResponseDto =  modelMapper.map(userFetched, LoginResponseDto.class);
		loginResponseDto.setMessage("LogIn SuccessFull.....!");
		return loginResponseDto;
	}

	private List<Object> userIdValidation(String userId) {
		String userIdCredentials = "^[a-zA-Z0-9]+([a-zA-Z0-9](_|-|@)[a-zA-Z0-9])*[a-zA-Z0-9]+";
		List<Object> userIdValidation = new ArrayList<Object>();

		if (userId.length() > 20) {
			userIdValidation.add(false);
			userIdValidation.add("UserId Should not be more than 20 Character...! ");
		} else if (userRepository.existsById(userId)) {
			userIdValidation.add(false);

			userIdValidation.add("User Id Not Available");
		}

		else if (!userId.matches(userIdCredentials)) {
			
			userIdValidation.add(false);

			userIdValidation.add("Wrong User Id Format");
		} else {
			
			userIdValidation.add(true);

			
			userIdValidation.add("userId : " + userId);
		}
		return userIdValidation;
	}

	private List<Object> nameValidation(String name) {
		String userNameCredentials = "^[a-zA-Z]+([a-zA-Z](_|-|@| )[a-zA-Z])*[a-zA-Z]+";
		List<Object> userNameValidation = new ArrayList<Object>();
		if (name.length() > 30) {
			userNameValidation.add(false);
			userNameValidation.add("User Name Size Exceeds");
		} else if (!name.matches(userNameCredentials)) {
			userNameValidation.add(false);
			userNameValidation.add("Wrong User Name Format");
		} else {
			userNameValidation.add(true);
			userNameValidation.add("userName : " + name);
		}
		return userNameValidation;
	}

	private List<Object> phoneNumberValidation(String phoneNumber1) {
		String phoneNumberCredentials = "^[9876]\\d{9}$";
		List<Object> phonenumberValidation = new ArrayList<Object>();
		if (userRepository.existsByphoneNumber(phoneNumber1)) {
			phonenumberValidation.add(false);
			phonenumberValidation.add("Phone Number is Already registered");

		} else if (!phoneNumber1.matches(phoneNumberCredentials)) {
			phonenumberValidation.add(false);
			phonenumberValidation.add("Phone Number Is Invalid");
		} else {
			phonenumberValidation.add(true);
			phonenumberValidation.add("User Phone Number :" + phoneNumber1);
		}
		return phonenumberValidation;
	}

	private List<Object> emailIdVerification(String email) {
		String emailCredentials = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
				+ "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,3}$";
		List<Object> emailValidation = new ArrayList<Object>();
		if (userRepository.existsByemailId(email)) {
			emailValidation.add(false);
			emailValidation.add("Email Already Registered");
		} else if (email.length() > 25) {
			emailValidation.add(false);
			emailValidation.add("Email Id should not contain more than 25 Characters");
		} else if (!email.matches(emailCredentials)) {
			emailValidation.add(false);
			emailValidation.add("Wrong Email Format");
		} else {
			emailValidation.add(true);
			emailValidation.add("userEmail Id :" + email);
		}
		return emailValidation;
	}

	private List<Object> passwordValidation(String password) {
		String userPasswordCredentials = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,10})";
		List<Object> passwordvalidation = new ArrayList<Object>();
		if (password.length() < 8 || password.length() > 10) {
			passwordvalidation.add(false);
			passwordvalidation.add(properties.getProperty("PASSWORD_LENGTH_EXCEED"));
	}
		if (!password.matches(userPasswordCredentials)) {
			passwordvalidation.add(false);
			passwordvalidation.add(properties.getProperty("INVALID_PASSWORD_CREDENTIALS"));
		}
		return passwordvalidation;
	}

	private List<Object> addressValidation(String address) {
		List<Object> addressValidation = new ArrayList<Object>();

		if (address == null) {
			addressValidation.add(false);
			addressValidation.add("Address Should not be Null");
		} else if (address.length() > 100) {
			addressValidation.add(false);
			addressValidation.add("Address Size Exceeds");
		} else {
			addressValidation.add(true);
			addressValidation.add("User Address :" + address);
		}
		return addressValidation;
	}

	@Override
	public UserResponseDto updateBalanceController(String userId,double bill){
		User user = userRepository.findByUserId(userId);
		user.setYoyoBalance(user.getYoyoBalance() - bill);
		user = userRepository.saveAndFlush(user);
		return modelMapper.map(user, UserResponseDto.class);
	}
	
	//--------------------->TestUpdate------------
	public UserResponseDto updateTestBalanceController(String userId,double bill){
		User user = userRepository.findByUserId(userId);
		user.setYoyoBalance(user.getYoyoBalance() - bill);
		user = userRepository.saveAndFlush(user);
		return modelMapper.map(user, UserResponseDto.class);
	}

	@Override
	@Transactional
	public UserResponseDto verifyYoyoBalance(double yoyoBalance, String userId) throws ServiceException{
		try {
			User user = userRepository.findByUserId(userId);
			if (user.getYoyoBalance() < yoyoBalance) {
				throw new InSufficientBalance("INSUFFICIENT_BALANCE");
			}
			user.setYoyoBalance(user.getYoyoBalance() - yoyoBalance);
			user = userRepository.saveAndFlush(user);
			return modelMapper.map(user, UserResponseDto.class);
		}catch(InSufficientBalance ex) {
			throw new ServiceException(ex.getMessage());
		}
		
	}

}
