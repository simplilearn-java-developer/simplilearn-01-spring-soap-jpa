package com.simplilearn.spring.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.simplilearn.soap.GetUserRequest;
import com.simplilearn.soap.GetUserResponse;
import com.simplilearn.soap.GetUsersRequest;
import com.simplilearn.soap.GetUsersResponse;
import com.simplilearn.spring.service.UserService;

@Endpoint
public class UserEndpoint {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String NAMESPACE_URI = "http://simplilearn.com/soap";

    @Autowired
    UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {

        logger.debug("Getting User: {}", request.getUsername());

        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.findUser(request.getUsername()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
    @ResponsePayload
    public GetUsersResponse getUser(@RequestPayload GetUsersRequest request) {

        logger.debug("Getting Users");

        GetUsersResponse response = new GetUsersResponse();
        response.getUsers().addAll(this.userService.listUsers());

        return response;
    }
}
