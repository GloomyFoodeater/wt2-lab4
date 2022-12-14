package service.impl;

import dao.DAOfactory;
import dao.interfaces.RoleDAO;
import dao.interfaces.UserDAO;
import dao.interfaces.UserInfoDAO;
import entity.Role;
import entity.User;
import entity.UserInfo;
import entity.UserOrder;
import service.interfaces.UserInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserInterface {
    private static final String user = "user";

    public Optional<entity.User> login(String email) throws Exception {
        if (email == null) {
            return Optional.empty();
        }
        try {
            UserDAO userDAO = DAOfactory.getInstance().getUserDAO();
            return userDAO.findByEmail(email);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public List<User> getUsersFromOrders(List<UserOrder> orders) throws Exception {
        List<User> users = new LinkedList<>();
        try {
            for (UserOrder order : orders) {
                Optional<User> user = retrieveUserById(order.getUserId());
                if (user.isPresent()) {
                    if (!users.contains(user.get())) {
                        users.add(user.get());
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return users;
    }

    public boolean register(String name, String email, String phone) throws Exception {
        if (name == null || email == null || phone == null) {
            return false;
        }

        try {
            UserDAO userDao = DAOfactory.getInstance().getUserDAO();
            if (userDao.findByEmail(email).isPresent()) {
                return false;
            }
            RoleDAO roleDAO = DAOfactory.getInstance().getRoleDAO();
            Optional<Role> role = roleDAO.findByRole(user);
            if (role.isEmpty()) {
                return false;
            }

            UserInfoDAO userInformationDAO = DAOfactory.getInstance().getUserInformationDAO();
            UserInfo userInformation = buildUserInfo(name, phone);
            int userInformationId = userInformationDAO.save(userInformation);

            User user = buildUser(email, userInformationId, role.get().getId());
            userDao.save(user);

            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public Optional<User> retrieveUserById(int userId) throws Exception {
        try {
            UserDAO userDao = DAOfactory.getInstance().getUserDAO();
            Optional<User> result;
            result = userDao.findById(userId);
            return result;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    private UserInfo buildUserInfo(String name, String phone) {
        UserInfo userInformation = new UserInfo();
        userInformation.setName(name);
        userInformation.setPhone(phone);
        return userInformation;
    }

    private User buildUser(String email, int userInformationId, int roleId) {
        User user = new User();
        user.setEmail(email);
        user.setUserInformationId(userInformationId);
        user.setRoleId(roleId);
        return user;
    }
}
