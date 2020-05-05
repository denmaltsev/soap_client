package ru.home.soap_client.repository;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.home.springsoap.entity.GetUserListRequest;
import ru.home.springsoap.entity.GetUserListResponse;
import ru.home.springsoap.entity.GetUserRequest;
import ru.home.springsoap.entity.GetUserResponse;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by Denis Maltsev on 05.05.2020.
 */
public class UserRepository extends WebServiceGatewaySupport {

    public GetUserResponse getUser(@NotNull @NotEmpty String url, @NotNull @NotEmpty String name) {

        GetUserRequest request = new GetUserRequest();
        request.setName(name);
        return (GetUserResponse) getWebServiceTemplate().marshalSendAndReceive(url, request);
    }

    public GetUserListResponse getAllUsers(@NotNull @NotEmpty String url) {
        GetUserListRequest request = new GetUserListRequest();
        return (GetUserListResponse) getWebServiceTemplate().marshalSendAndReceive(url, request);
    }
}
