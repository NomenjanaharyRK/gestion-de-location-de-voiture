package interfaces;

import java.util.List;

import model.Location;

public interface ILocation {
	public Location findOne(Long id);
	public List<Location> findAll();
	public List<Location> findByFinished();
	public List<Location> findActual();
	public List<Location> findByLate();
	
	public Location create(Location location);
	public Location update(Location location);
	public void delete(Long id);
	public void terminer(Long id);
}
