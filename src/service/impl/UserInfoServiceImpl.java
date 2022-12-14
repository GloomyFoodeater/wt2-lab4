package service.impl;

import dao.DAOfactory;
import dao.interfaces.UserInfoDAO;
import entity.User;
import entity.UserInfo;
import service.interfaces.UserInfoInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInfoServiceImpl implements UserInfoInterface {
    public Optional<UserInfo> retrieveUserInfoById(int userInformationId) throws Exception {
        try {
            UserInfoDAO userInformationDAO = DAOfactory.getInstance().getUserInformationDAO();
            return userInformationDAO.findById(userInformationId);
        }catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public List<UserInfo> getUserInfoFromUsers(List<User> users) throws Exception {
        List<UserInfo> result = new ArrayList<>();
        try {
            for (User user : users) {
                Optional<UserInfo> userInformation = retrieveUserInfoById(user.getUserInformationId());
                userInformation.ifPresent(result::add);
            }
        }catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return result;
    }
}
