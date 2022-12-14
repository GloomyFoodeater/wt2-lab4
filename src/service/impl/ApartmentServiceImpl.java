package service.impl;

import dao.DAOfactory;
import dao.interfaces.ApartmentDAO;
import dao.interfaces.UserOrderDAO;
import entity.Apartment;
import entity.UserOrder;
import service.interfaces.ApartmentInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApartmentServiceImpl implements ApartmentInterface {

    public List<Apartment> retrieveApartmentByStatus(String status) throws Exception {
        try {
            ApartmentDAO apartmentDAO = DAOfactory.getInstance().getApartmentDAO();
            return apartmentDAO.findByStatus(status);
        }catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public boolean addNewApartment(String status, String number, String price) throws Exception {
        if (status == null || number == null || price == null) {
            return false;
        }

        double dblPrice;
        int intNumber;
        try {
            dblPrice = Double.parseDouble(price);
            intNumber = Integer.parseInt(number);
        }catch(NumberFormatException e) {
            throw new Exception(e.getMessage(), e);
        }

        ApartmentDAO apartmentDAO = DAOfactory.getInstance().getApartmentDAO();
        Apartment apartment = buildApartment(status, intNumber, dblPrice);
        try {
            apartmentDAO.save(apartment);
            return true;
        }catch(Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public List<Apartment> retrieveApartmentsByUserOrders(List<UserOrder> userOrders) throws Exception {
        try {
            ApartmentDAO apartmentDAO = DAOfactory.getInstance().getApartmentDAO();
            List<Apartment> result = new ArrayList<>();
            for (UserOrder userOrder : userOrders) {
                result.add(apartmentDAO.findById(userOrder.getApartmentId()).get());
            }
            return result;
        }catch(Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public List<Apartment> retrieveApartmentsByUserId(int userId) throws Exception {
        try {
            UserOrderDAO userOrderDao=DAOfactory.getInstance().getUserOrderDAO();
            List<UserOrder> userOrders=userOrderDao.findByUserId(userId);
            List<Apartment> result=new ArrayList<>();
            ApartmentDAO apartmentDao= DAOfactory.getInstance().getApartmentDAO();
            for (UserOrder userOrder : userOrders) {
                result.add(apartmentDao.findById(userOrder.getApartmentId()).get());
            }
            return result;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public Optional<Apartment> retrieveApartmentById(int apartmentId) throws Exception {
        try {
            ApartmentDAO apartmentDAO = DAOfactory.getInstance().getApartmentDAO();
            return apartmentDAO.findById(apartmentId);
        }catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public void removeApartmentById(int apartmentId) throws Exception {
        try {
            ApartmentDAO apartmentDAO= DAOfactory.getInstance().getApartmentDAO();
            UserOrderDAO userOrderDAO=DAOfactory.getInstance().getUserOrderDAO();

            List<UserOrder> userOrders=userOrderDAO.findByApartmentId(apartmentId);
            for (UserOrder userOrder : userOrders) {
                userOrderDAO.removeById(userOrder.getId());
            }
            apartmentDAO.removeById(apartmentId);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public List<Apartment> retrieveAllApartments() throws Exception {
        try {
            ApartmentDAO apartmentDAO = DAOfactory.getInstance().getApartmentDAO();
            return apartmentDAO.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public boolean updateApartmentInfo(String id, String status, String number, String price) throws Exception {
        if(status == null || price == null){
            return false;
        }

        double dblPrice;
        int intId;
        int intNumber;
        try{
            dblPrice = Double.parseDouble(price);
            intId = Integer.parseInt(id);
            intNumber = Integer.parseInt(number);
        }catch (NumberFormatException e) {
            throw new Exception(e.getMessage(), e);
        }

        ApartmentDAO apartmentDAO = DAOfactory.getInstance().getApartmentDAO();
        Apartment apartment = buildApartment(status, intNumber, dblPrice);
        try {
            apartmentDAO.updateApartmentById(intId, apartment);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public void updateApartmentStatusById(int id, String status) throws Exception {
        ApartmentDAO apartmentDAO = DAOfactory.getInstance().getApartmentDAO();
        try {
            apartmentDAO.updateApartmentStatusById(id,status);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    private Apartment buildApartment(String status, int number, double price) {
        Apartment apartment = new Apartment();
        apartment.setStatus(status);
        apartment.setNumber(number);
        apartment.setPrice(price);
        return apartment;
    }
}
