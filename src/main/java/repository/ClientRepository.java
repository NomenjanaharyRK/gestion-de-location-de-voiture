package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import interfaces.IClient;
import model.Client;

public class ClientRepository implements IClient {

	@Override
	public Client findOne(Long id) {
		Client client = null;
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM CLIENT WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
			    client = new Client();
				client.setId(rs.getLong("id"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAdresse(rs.getString("adresse"));
				client.setTelephone(rs.getString("telephone"));
				client.setCin(rs.getString("cin"));
				
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return client;
	}

	@Override
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM CLIENT");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getLong("id"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAdresse(rs.getString("adresse"));
				client.setTelephone(rs.getString("telephone"));
				client.setCin(rs.getString("cin"));
				clients.add(client);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clients;
	}

	@Override
	public Client create(Client client) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO CLIENT (NOM,PRENOM,TELEPHONE,ADRESSE,CIN) VALUES (?,?,?,?,?)");
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getTelephone());
			ps.setString(4, client.getAdresse());
			ps.setString(5, client.getCin());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public Client update(Client client) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE CLIENT SET NOM = ?, PRENOM = ?, TELEPHONE = ?, ADRESSE = ?, CIN = ? WHERE ID=?");
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getTelephone());
			ps.setString(4, client.getAdresse());
			ps.setString(5, client.getCin());
			ps.setLong(6, client.getId());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public void delete(Long id) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM CLIENT WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
