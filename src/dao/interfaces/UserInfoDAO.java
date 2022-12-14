package dao.interfaces;

import dao.DAO;
import entity.UserInfo;

public interface UserInfoDAO extends DAO<UserInfo> {

    void updateById(int id, UserInfo userInformation) throws Exception;
}
