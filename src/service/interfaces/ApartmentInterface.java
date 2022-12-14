package service.interfaces;

import entity.Apartment;
import entity.UserOrder;

import java.util.List;
import java.util.Optional;

public interface ApartmentInterface {
    List<Apartment> retrieveAllApartments() throws Exception;
    Optional<Apartment> retrieveApartmentById(int apartmentId) throws Exception;
    void removeApartmentById(int userId) throws Exception;
    List<Apartment> retrieveApartmentByStatus(String status) throws Exception;
    List<Apartment> retrieveApartmentsByUserOrders(List<UserOrder> userOrder) throws Exception;
    boolean addNewApartment(String status, String number, String price) throws Exception;
    void updateApartmentStatusById(int id, String status) throws Exception;
    boolean updateApartmentInfo(String id, String status, String number, String price) throws Exception;
    List<Apartment> retrieveApartmentsByUserId(int userId) throws Exception;
}
