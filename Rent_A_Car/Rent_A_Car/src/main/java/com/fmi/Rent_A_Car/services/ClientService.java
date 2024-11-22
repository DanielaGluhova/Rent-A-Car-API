package com.fmi.Rent_A_Car.services;


import com.fmi.Rent_A_Car.entities.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class ClientService {

    private final JdbcTemplate db;

    public ClientService(JdbcTemplate db) {
        this.db = db;
    }

    // Създаване на нов клиент
    public boolean createClient(Client client) {
        String query = "INSERT INTO clients (name, address, phone, age, has_incidents, is_deleted) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        db.update(query, client.getName(), client.getAddress(), client.getPhone(),
                client.getAge(), client.getHas_incidents(), client.getIs_deleted());
        return true;
    }

    // Листване на всички клиенти
    public List<Client> getAllClients() {
        String query = "SELECT * FROM clients WHERE is_deleted = 0";
        return db.query(query, (rs, rowNum) -> new Client(rs.getInt("id"), rs.getString("name"),
                rs.getString("address"), rs.getString("phone"),
                rs.getInt("age"), rs.getInt("has_incidents"),
                rs.getInt("is_deleted")));
    }

    // Листване на клиент по ID
    public Client getClient(int id) {
        String query = "SELECT * FROM clients WHERE id = ? AND is_deleted = 0";
        List<Client> clients = db.query(query, new Object[]{id},
                (rs, rowNum) -> new Client(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                        rs.getString("phone"), rs.getInt("age"), rs.getInt("has_incidents"),
                        rs.getInt("is_deleted")));
        if (clients.isEmpty()) {
            return null;
        }
        return clients.get(0);
    }

    // Актуализация на данни за клиент
    public boolean updateClient(Client client) {
        String query = "UPDATE clients SET name = ?, address = ?, phone = ?, age = ?, has_incidents = ? " +
                "WHERE id = ? AND is_deleted = 0";
        int resultCount = db.update(query, client.getName(), client.getAddress(), client.getPhone(),
                client.getAge(), client.getHas_incidents(), client.getId());
        return resultCount == 1;
    }

    // Изтриване на клиент (маркиране като изтрит)
    public boolean deleteClient(int id) {
        String query = "UPDATE clients SET is_deleted = 1 WHERE id = ? AND is_deleted = 0";
        int resultCount = db.update(query, id);
        return resultCount == 1;
    }
}

