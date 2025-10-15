package com.anonymousv18.profile.service.impl;

import com.anonymousv18.profile.dto.request.UserProfileSubRequest;
import com.anonymousv18.profile.dto.response.AddressResponse;
import com.anonymousv18.profile.dto.response.ApiResponse;
import com.anonymousv18.profile.dto.response.UserProfileSubResponse;
import com.anonymousv18.profile.entity.UserProfileEntity;
import com.anonymousv18.profile.entity.UserProfileSubEntity;
import com.anonymousv18.profile.exception.AppException;
import com.anonymousv18.profile.exception.ErrorCode;
import com.anonymousv18.profile.mapper.IUserProfileMapper;
import com.anonymousv18.profile.repository.IUserProfileRepository;
import com.anonymousv18.profile.repository.IUserProfileSubRepository;
import com.anonymousv18.profile.repository.httpclient.IAddressRepository;
import com.anonymousv18.profile.service.IUserProfileSubService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileSubService implements IUserProfileSubService {

    IUserProfileSubRepository userProfileSubRepository;
    IUserProfileRepository userProfileRepository;
    IUserProfileMapper userProfileMapper;
    IAddressRepository addressRepository;

    @Override
    public UserProfileSubResponse insert(String userProfileId, UserProfileSubRequest userProfileSubRequest) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_NOT_FOUND));
        ApiResponse<AddressResponse> addressResponse = addressRepository.insert(userProfileSubRequest.getAddress());
        UserProfileSubEntity userProfileSubEntity = UserProfileSubEntity.builder()
                .name(userProfileSubRequest.getName())
                .userProfile(userProfileEntity)
                .phoneNumber(userProfileSubRequest.getPhoneNumber())
                .addressId(addressResponse.getResult().getId())
                .build();
        userProfileSubEntity = userProfileSubRepository.save(userProfileSubEntity);
        return UserProfileSubResponse.builder()
                .id(userProfileSubEntity.getId())
                .name(userProfileSubEntity.getName())
                .phoneNumber(userProfileSubEntity.getPhoneNumber())
                .address(addressResponse.getResult())
                .build();
    }

    @Override
    public UserProfileSubResponse update(String id, UserProfileSubRequest userProfileSubRequest) {
        UserProfileSubEntity userProfileSubEntity = userProfileSubRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_SUB_NOT_FOUND));
        ApiResponse<AddressResponse> addressResponse = addressRepository.update(
                userProfileSubEntity.getAddressId()
                , userProfileSubRequest.getAddress()
        );
        userProfileSubEntity.setName(userProfileSubRequest.getName());
        userProfileSubEntity.setPhoneNumber(userProfileSubRequest.getPhoneNumber());
        userProfileSubEntity.setAddressId(addressResponse.getResult().getId());
        userProfileSubEntity = userProfileSubRepository.save(userProfileSubEntity);
        return UserProfileSubResponse.builder()
                .id(userProfileSubEntity.getId())
                .name(userProfileSubEntity.getName())
                .phoneNumber(userProfileSubEntity.getPhoneNumber())
                .address(addressResponse.getResult())
                .build();
    }

    @Override
    public void delete(String id) {
        UserProfileSubEntity userProfileSubEntity = userProfileSubRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_SUB_NOT_FOUND));
        addressRepository.delete(userProfileSubEntity.getAddressId());
        userProfileSubRepository.deleteById(id);
    }

    @Override
    public List<UserProfileSubResponse> findByUserProfile_Id(String userProfileId) {
        List<UserProfileSubEntity> userProfileSubEntities = userProfileSubRepository.findByUserProfile_Id(userProfileId);
        return userProfileSubEntities.stream()
                .map(userProfileSubEntity -> UserProfileSubResponse.builder()
                        .id(userProfileSubEntity.getId())
                        .name(userProfileSubEntity.getName())
                        .phoneNumber(userProfileSubEntity.getPhoneNumber())
                        .address(addressRepository.findById(userProfileSubEntity.getAddressId()).getResult())
                        .build())
                .toList();
    }
}
