//package com.mindtree.userservice;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.mindtree.userservice.controller.UserController;
//import com.mindtree.userservice.dto.AddBalane;
//import com.mindtree.userservice.dto.AddYoYoBalanceDto;
//import com.mindtree.userservice.dto.LoginDto;
//import com.mindtree.userservice.dto.LoginResponseDto;
//import com.mindtree.userservice.dto.UpdateProfileDto;
//import com.mindtree.userservice.dto.UpdateResponseDto;
//import com.mindtree.userservice.dto.UserDto;
//import com.mindtree.userservice.dto.UserResponseDto;
//import com.mindtree.userservice.exception.UserServiceException;
//import com.mindtree.userservice.model.BrandModel;
//import com.mindtree.userservice.model.CategoryModel;
//import com.mindtree.userservice.model.ModelModel;
//import com.mindtree.userservice.model.ProductModel;
//import com.mindtree.userservice.service.UserService;
//
//
//@SpringBootTest
////@RunWith(SpringRunner.class)
//class UserServiceApplicationTests {
//
//	@InjectMocks 
//	private UserController userController;
//	
//	@Mock
//	private UserService userService;
//	
//	@Mock
//	private ModelMapper modelMapper;
//	
//	private UserDto userDto;
//	
//	private AddBalane addBalance1;
//	private String userId;
//	private AddYoYoBalanceDto addBalanceDto;
//	private Map<String, Object> response;
//	private UpdateProfileDto updateDetails;
//	private UpdateResponseDto updateResponse;
//	private Map<String, Object> obtainedResponse;
//	private Map<String, Object> logInResponseMessage;
//	private UserResponseDto useResponseDto;
//	private LoginDto loginDto;
//	private LoginResponseDto loginResponseDto;
//	private ProductModel productModel;
//	
//	@BeforeEach
//	private void setUp()
//	{
//		MockitoAnnotations.initMocks(this);
//		userDto=getUser();
//		addBalance1 = getAddBalance();
//		userId = "nit96";
//		addBalanceDto = getAddYoyoBalanceDto();
//		response = getResponse();
//		updateDetails = getUpdateProfileDto();
//		updateResponse = getUpdateResponse();
//		obtainedResponse = obtainedResponse1();
//		useResponseDto = getUserProfile();
//		loginDto = getLoginDto();
//		loginResponseDto  = getLoginResponse();
//		logInResponseMessage = getlogInresponseMap();
//		
//	
//	}
//
//	
//	private Map<String, Object> getlogInresponseMap(){
//		Map<String , Object> loggedinResp = new LinkedHashMap<String, Object>();
//		loggedinResp.put("Status", "User Successfully Login");
//		loggedinResp.put("Body", "User Profile Details ");
//		loggedinResp.put("Details", loginResponseDto);
//		return loggedinResp;
//	}
//	
//	private LoginResponseDto getLoginResponse() {
//		LoginResponseDto loginResp = new LoginResponseDto();
//		loginResp.setUserId("nit96");
//		loginResp.setUserName("Nanda");
//		loginResp.setYoyoBalance("20.00");
//		return loginResp;
//	}
//	
//	private LoginDto getLoginDto() {
//		LoginDto logValues = new LoginDto();
//		logValues.setUserId("nit96");
//		logValues.setPassword("Nithin@26");
//		return logValues;
//	}
//	private UserResponseDto getUserProfile() {
//		UserResponseDto userResponse = new UserResponseDto();
//		userResponse.setAddress("BAngalore");
//		userResponse.setEmailId("rtyui@gmail.com");
//		userResponse.setUserId("nit96");
//		userResponse.setUserName("NAnda");
//		userResponse.setPassword("Nithi@123");
//		userResponse.setPhoneNumber("8899778877");
//		userResponse.setYoyoBalance(20);
//		return userResponse;
//		
//	}
//	
//	
//	private Map<String, Object> obtainedResponse1(){
//		Map<String, Object> newResponse = new LinkedHashMap<String, Object>();
//		newResponse.put("Status", "YOYO Balance Successfully Updated");
//		newResponse.put("Body", "New Updated YoYo Balance Details--- ");
//		return newResponse;
//	}
//	
//	
//	private Map<String, Object> getResponse(){
//		Map<String, Object> newResponse = new LinkedHashMap<String, Object>();
//		newResponse.put("Status", "YOYO Balance Successfully Updated");
//		newResponse.put("Body", "New Updated YoYo Balance Details--- ");
//		newResponse.put("Details",addBalanceDto);
//		return newResponse;
//	}
//	
//	public UpdateResponseDto getUpdateResponse() {
//		UpdateResponseDto updateResp = new UpdateResponseDto();
//		List<String> updated = new ArrayList<String>();
//		List<String> error = new ArrayList<String>();
//		updated.add("Bangalore");
//		updated.add("niht@gmail.com");
//		updated.add("Nanda");
//		updated.add("9988776688");
//		updateResp.setUpdatedResponse(updated);
//		error.add("No Errors");
//		updateResp.setErrorsInUpdate(error);
//		return updateResp;
//	}
//	
//	private AddYoYoBalanceDto getAddYoyoBalanceDto() {
//		AddYoYoBalanceDto addBalanceDto = new AddYoYoBalanceDto();
//		addBalanceDto.setAmountToBuyPoints(100);
//		addBalanceDto.setPointsPurchased(25);
//		addBalanceDto.setUserName("Nanda");
//		addBalanceDto.setYoYoBalance(25);
//		return addBalanceDto;
//	}
//	
//	private AddBalane getAddBalance() {
//		AddBalane addBalance = new AddBalane();
//		addBalance.setAmountToBuyPoints(10);
//		return addBalance;
//	}
//	
//	private UserDto getUser()
//	{
//		UserDto userDto = new UserDto();
//		userDto.setUserId("14267");
//		userDto.setUserName("askha");
//		userDto.setAddress("dgfhgwjq576");
//		userDto.setEmailId("ahsutawgav12dkap");
//		userDto.setPhoneNumber("51281");
//		userDto.setPassword("#471JL);*");
//		return userDto;
//	}
//
//		private UpdateProfileDto getUpdateProfileDto() {
//			UpdateProfileDto update = new UpdateProfileDto();
//			update.setAddress("Bangalore");
//			update.setEmailId("niht@gmail.com");
//			update.setUserName("Nanda");
//			update.setPhoneNumber("9988776688");
//			return update;
//		}
// 
//	@Test
//	public void testUserSignUp() throws UserServiceException
//	{
//		ResponseEntity<Map<String, Object>> testResult = userController.userSignUp(userDto);
//		assertEquals(HttpStatus.OK, testResult.getStatusCode());
//	}
//	
//	@Test
//	public void testAddYoyoBalance() throws UserServiceException {
//	when(userService.addYoyoBalance(addBalance1, userId)).thenReturn(addBalanceDto);
//		ResponseEntity<Map<String, Object>> addBalanceDto1 = userController.addYoyoBalance(addBalance1, userId);
//		assertEquals(response,addBalanceDto1.getBody());
//	}
//	@Test
//	public void testUpdateProfile() throws UserServiceException {
//		when(userService.updateProfile(updateDetails, userId)).thenReturn(updateResponse);
//		UpdateResponseDto result1 = userController.updateProfile(updateDetails, userId);
//		assertEquals(updateResponse,result1);
//	}
//	
//	@Test
//	public void testGetProfileDto() throws UserServiceException {
//		when(userService.profileDetails(userId)).thenReturn(useResponseDto);
//		UserResponseDto userRespResult = userController.ProfileDetails(userId);
//		assertEquals(useResponseDto,userRespResult); 
//	}
//	
//	
//}
