package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.ApiReturnData;
import com.e_commerce.e_commerce.model.ApiStatus;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class UserManagementService {
    private String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private String nameRegex = "^[A-Za-z0-9]{6,}$";

    @Autowired
    private ApiReturnData apiReturnData;

    @Autowired
    private UserManagementDAO daoManger;


    public ApiReturnData saveUser(UserData data) {
        ApiReturnData response = new ApiReturnData();

        if (data.getUserName() == null || !Pattern.matches(nameRegex, data.getUserName())) {
            response.setApiResponseStatus(ApiStatus.ERROR);
            response.setApiResponseMessage("Username must be at least 6 characters and contain only letters or numbers");
            return response;
        }

        if (data.getEmail() == null || !Pattern.matches(emailRegex, data.getEmail())) {
            response.setApiResponseStatus(ApiStatus.ERROR);
            response.setApiResponseMessage("Invalid email format");
            return response;
        }

        if (data.getUserRole() == null) {
            response.setApiResponseStatus(ApiStatus.ERROR);
            response.setApiResponseMessage("User role must be provided");
            return response;
        }

        try{

            Optional<UserData> resultData = daoManger.findByEmail(data.getEmail());
            if (resultData.isPresent()) {
                response.setApiResponseStatus(ApiStatus.ERROR);
                response.setApiResponseMessage("Email is already registered");
                response.setApiResponseData(null);
                return response;
            }

            UserData savedUser = daoManger.save(data);
            response.setApiResponseStatus(ApiStatus.SUCCESS);
            response.setApiResponseMessage("User saved successfully");
            response.setApiResponseData(savedUser);
        }
        catch (Exception e){
            response.setApiResponseStatus(ApiStatus.SERVERERROR);
            response.setApiResponseMessage("Unexpeted Server Error Please Try after Some Time");
        }
        return response;
    }

    public ApiReturnData checkLogin(UserData userdata) {
        ApiReturnData apiReturnData = new ApiReturnData();
        Optional<UserData> resultData = daoManger.findByEmail(userdata.getEmail());

        if (resultData.isPresent()) {
            UserData existingUser = resultData.get();
            String storedPassword = existingUser.getPassWord();

            if(!existingUser.getUserRole().equals(userdata.getUserRole())){
                apiReturnData.setApiResponseStatus(ApiStatus.ERROR);
                apiReturnData.setApiResponseMessage("Roll Not Matched");
                apiReturnData.setApiResponseData(null);

                return apiReturnData;
            }

            if (storedPassword.equals(userdata.getPassWord())) {
                apiReturnData.setApiResponseStatus(ApiStatus.SUCCESS);
                apiReturnData.setApiResponseMessage("Login successful");
                apiReturnData.setApiResponseData(existingUser);
            } else {
                apiReturnData.setApiResponseStatus(ApiStatus.ERROR);
                apiReturnData.setApiResponseMessage("Incorrect password");
                apiReturnData.setApiResponseData(null);
            }
        } else {
            apiReturnData.setApiResponseStatus(ApiStatus.ERROR);
            apiReturnData.setApiResponseMessage("No email found in user data");
            apiReturnData.setApiResponseData(null);
        }

        return apiReturnData;
    }

}
