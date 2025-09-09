package com.e_commerce.e_commerce.controller.restcontroller;

import com.e_commerce.e_commerce.model.ApiReturnData;
import com.e_commerce.e_commerce.model.ApiStatus;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserManagementRest {

    @Autowired
    private UserManagementService userService;

    @PostMapping("/signUp")
    public ResponseEntity<ApiReturnData> signUp(@RequestBody UserData userData) {
        ApiReturnData response = userService.saveUser(userData);

        if (response.getApiResponseStatus() == ApiStatus.SUCCESS) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiReturnData> login(@RequestBody UserData userdata){

        ApiReturnData returnData = userService.checkLogin(userdata);

        if(returnData.getApiResponseStatus() == ApiStatus.ERROR){
            return ResponseEntity.badRequest().body(returnData);
        }
        else{
            return ResponseEntity.ok(returnData);
        }
    }

}
