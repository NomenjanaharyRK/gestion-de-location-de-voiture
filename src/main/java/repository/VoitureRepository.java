package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import interfaces.IVoiture;
import model.Voiture;

public class VoitureRepository implements IVoiture {
	
	@Override
	public Voiture findOne(Long id) {
		Voiture voiture = null;
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VOITURE WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				voiture = new Voiture();
				voiture.setId(id);
				voiture.setMarque(rs.getString("marque"));
				voiture.setModele(rs.getString("modele"));
				voiture.setAnnee(rs.getInt("annee"));
				voiture.setCouleur(rs.getString("couleur"));
				voiture.setPrixJournalier(rs.getDouble("prix"));
				voiture.setStatus(rs.getBoolean("status"));
				voiture.setIllustration(rs.getString("illustration"));
				voiture.setNbPlace(rs.getInt("nb_place"));
				voiture.setDescription(rs.getString("description"));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voiture;
	}

	@Override
	public List<Voiture> findAll() {
		List<Voiture> voitures = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VOITURE");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Voiture voiture = new Voiture();
				voiture.setId(rs.getLong("id"));
				voiture.setMarque(rs.getString("marque"));
				voiture.setModele(rs.getString("modele"));
				voiture.setCouleur(rs.getString("couleur"));
				voiture.setAnnee(rs.getInt("annee"));
				voiture.setPrixJournalier(rs.getDouble("prix"));
				voiture.setStatus(rs.getBoolean("status"));
				voiture.setIllustration(rs.getString("illustration"));
				voiture.setNbPlace(rs.getInt("nb_place"));
				voiture.setDescription(rs.getString("description"));
				voitures.add(voiture);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voitures;
	}

	@Override
	public Voiture create(Voiture voiture) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO VOITURE (MARQUE,MODELE,COULEUR,PRIX,ANNEE,STATUS,ILLUSTRATION,NB_PLACE,DESCRIPTION) VALUES (?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1, voiture.getMarque());
			ps.setString(2, voiture.getModele());
			ps.setString(3, voiture.getCouleur());
			ps.setDouble(4, voiture.getPrixJournalier());
			ps.setInt(5, voiture.getAnnee());
			ps.setBoolean(6, voiture.isStatus());
			ps.setString(7, voiture.getIllustration());
			ps.setInt(8, voiture.getNbPlace());
			ps.setString(9, voiture.getDescription());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voiture;
	}

	@Override
	public Voiture update(Voiture voiture) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE VOITURE SET MARQUE=?,MODELE=?,COULEUR=?,ANNEE=?,PRIX=?,NB_PLACE=?, DESCRIPTION=? WHERE ID=?");
			ps.setString(1, voiture.getMarque());
			ps.setString(2, voiture.getModele());
			ps.setString(3, voiture.getCouleur());
			ps.setInt(4, voiture.getAnnee());
			ps.setDouble(5, voiture.getPrixJournalier());
			ps.setInt(6, voiture.getNbPlace());
			ps.setString(7, voiture.getDescription());
			ps.setLong(8, voiture.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voiture;
	}

	@Override
	public void delete(Long id) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM VOITURE WHERE ID=?");
			ps.setLong(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void rent(Long id) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE VOITURE SET STATUS=? WHERE ID=?");
			ps.setBoolean(1, false);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Voiture> findByMotCle(String motCle) {
		Connection connection = DatabaseConnection.getConnection();
		List<Voiture> voitures = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VOITURE WHERE MARQUE LIKE ? ");
			ps.setString(1, motCle);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Voiture voiture = new Voiture();
				voiture.setId(rs.getLong("id"));
				voiture.setMarque(rs.getString("marque"));
				voiture.setModele(rs.getString("modele"));
				voiture.setCouleur(rs.getString("couleur"));
				voiture.setAnnee(rs.getInt("annee"));
				voiture.setPrixJournalier(rs.getDouble("prix"));
				voiture.setIllustration(rs.getString("illustration"));
				voiture.setStatus(rs.getBoolean("status"));
				voiture.setNbPlace(rs.getInt("nb_place"));
				voiture.setDescription(rs.getString("description"));
				voitures.add(voiture);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return voitures;
	}

	@Override
	public List<Voiture> findAllDisponible() {
		List<Voiture> voitures = new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM VOITURE WHERE STATUS = ?");
			ps.setBoolean(1, true);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Voiture voiture = new Voiture();
				voiture.setId(rs.getLong("id"));
				voiture.setMarque(rs.getString("marque"));
				voiture.setModele(rs.getString("modele"));
				voiture.setCouleur(rs.getString("couleur"));
				voiture.setAnnee(rs.getInt("annee"));
				voiture.setPrixJournalier(rs.getDouble("prix"));
				voiture.setStatus(rs.getBoolean("status"));
				voiture.setNbPlace(rs.getInt("nb_place"));
				voiture.setDescription(rs.getString("description"));
				voitures.add(voiture);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voitures;
	}

	@Override
	public void setDisponible(Long id) {
		Connection connection = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE VOITURE SET STATUS=? WHERE ID=?");
			ps.setBoolean(1, true);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
