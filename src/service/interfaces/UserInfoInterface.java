package service.interfaces;

import entity.User;
import entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoInterface {
    Optional<UserInfo> retrieveUserInfoById(int userInfoId) throws Exception;
    List<UserInfo> getUserInfoFromUsers(List<User> users) throws Exception;
}
