package dao.interfaces;

import dao.DAO;
import entity.Apartment;

import java.util.List;

    public interface ApartmentDAO extends DAO<Apartment> {

        List<Apartment> findByStatus(String status) throws Exception;
        List<Apartment> findByPrice(double from, double to) throws Exception;
        void updateApartmentById(int id, Apartment apartment) throws Exception;
        void updateApartmentStatusById(int id, String status) throws Exception;
    }
