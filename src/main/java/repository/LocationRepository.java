package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import interfaces.IClient;
import interfaces.ILocation;
import interfaces.IVoiture;
import model.Client;
import model.Location;
import model.Voiture;

public class LocationRepository implements ILocation {
	
	private IClient clientRepository;
	private IVoiture voitureRepository;

	public LocationRepository() {
		super();
		clientRepository = new ClientRepository();
		voitureRepository = new VoitureRepository();
	}

	@Override
	public Location findOne(Long id) {
		Location location = null;
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LOCATION WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				location = new Location();
				location.setId(id);
				Long voitureId = rs.getLong("voiture");
				Long clientId = rs.getLong("client");
				
				Voiture voiture = voitureRepository.findOne(voitureId);
				Client client = clientRepository.findOne(clientId);
				
				location.setVoiture(voiture);
				location.setClient(client);
				location.setDateDebut(rs.getDate("date_debut"));
				location.setDateFin(rs.getDate("date_fin"));
				location.setPrix_total(rs.getDouble("prix_total"));
				location.setStatus(rs.getBoolean("status"));
			}
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}

	@Override
	public List<Location> findAll() {
		List<Location> locations = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LOCATION");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Location location = new Location();
				Voiture voiture = voitureRepository.findOne(rs.getLong("voiture"));
				Client client = clientRepository.findOne(rs.getLong("client"));
				location.setVoiture(voiture);
				location.setClient(client);
				location.setId(rs.getLong("id"));
				location.setDateDebut(rs.getDate("date_debut"));
				location.setDateFin(rs.getDate("date_fin"));
				location.setPrix_total(rs.getDouble("prix_total"));
				location.setStatus(rs.getBoolean("status"));
				locations.add(location);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locations;
	}

	@Override
	public Location create(Location location) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO location (voiture,client,date_debut,date_fin,prix_total,status) VALUES (?,?,?,?,?,?)");
			ps.setLong(1, location.getVoiture().getId());
			ps.setLong(2, location.getClient().getId());
			ps.setDate(3, (Date) location.getDateDebut());
			ps.setDate(4, (Date) location.getDateFin());
			ps.setDouble(5, location.getPrix_total());
			ps.setBoolean(6,location.isStatus());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return location;
	}

	@Override
	public Location update(Location location){
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE LOCATION SET VOITURE=?, CLIENT=?, DATE_DEBUT=?,DATE_FIN=?,PRIX_TOTAL=? WHERE ID=?");
			ps.setLong(1, location.getVoiture().getId());
			ps.setLong(2, location.getClient().getId());
			ps.setDate(3, (Date) location.getDateDebut());
			ps.setDate(4, (Date) location.getDateFin());
			ps.setDouble(5, location.getPrix_total());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}

	@Override
	public void delete(Long id) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM LOCATION WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void terminer(Long id) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE LOCATION SET STATUS=? WHERE ID=?");
			ps.setBoolean(1, true);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Location> findByFinished() {
		List<Location> locations = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LOCATION WHERE STATUS=?");
			ps.setBoolean(1, true);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Location location = new Location();
				Voiture voiture = voitureRepository.findOne(rs.getLong("voiture"));
				Client client = clientRepository.findOne(rs.getLong("client"));
				location.setVoiture(voiture);
				location.setClient(client);
				location.setId(rs.getLong("id"));
				location.setDateDebut(rs.getDate("date_debut"));
				location.setDateFin(rs.getDate("date_fin"));
				location.setPrix_total(rs.getDouble("prix_total"));
				location.setStatus(rs.getBoolean("status"));
				locations.add(location);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locations;
	}

	@Override
	public List<Location> findActual() {
		List<Location> locations = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LOCATION WHERE STATUS=?");
			ps.setBoolean(1, false);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Location location = new Location();
				Voiture voiture = voitureRepository.findOne(rs.getLong("voiture"));
				Client client = clientRepository.findOne(rs.getLong("client"));
				location.setVoiture(voiture);
				location.setClient(client);
				location.setId(rs.getLong("id"));
				location.setDateDebut(rs.getDate("date_debut"));
				location.setDateFin(rs.getDate("date_fin"));
				location.setPrix_total(rs.getDouble("prix_total"));
				location.setStatus(rs.getBoolean("status"));
				locations.add(location);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locations;
	}

	@Override
	public List<Location> findByLate() {
		List<Location> locations = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();
		try {
//			SELECT * FROM LOCATION WHERE DATEFIN < DATEDUJOUR AND STATUS = FALSE
			java.util.Date dateDuJourUtil = new java.util.Date();
			int day = dateDuJourUtil.getDate();
			int month = dateDuJourUtil.getMonth();
			int year = dateDuJourUtil.getYear();
			Date sqlDate = new Date(year, month, day);
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LOCATION WHERE STATUS=? AND  DATE_FIN < ?");
			ps.setBoolean(1, false);
			ps.setDate(2, sqlDate);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Location location = new Location();
				Voiture voiture = voitureRepository.findOne(rs.getLong("voiture"));
				Client client = clientRepository.findOne(rs.getLong("client"));
				location.setVoiture(voiture);
				location.setClient(client);
				location.setId(rs.getLong("id"));
				location.setDateDebut(rs.getDate("date_debut"));
				location.setDateFin(rs.getDate("date_fin"));
				location.setPrix_total(rs.getDouble("prix_total"));
				location.setStatus(rs.getBoolean("status"));
				locations.add(location);
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locations;
	}

}
