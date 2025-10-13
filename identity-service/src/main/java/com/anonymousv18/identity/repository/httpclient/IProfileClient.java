package com.anonymousv18.identity.repository.httpclient;

import com.anonymousv18.identity.configuration.AuthenticationRequestInterceptor;
import com.anonymousv18.identity.dto.request.ProfileCreationRequest;
import com.anonymousv18.identity.dto.response.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface IProfileClient {

    @PostMapping(value = "/internal/user/{userId}/profile/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createProfile(
            @PathVariable(name = "userId") String userId,
            @RequestBody ProfileCreationRequest request
    );

}
