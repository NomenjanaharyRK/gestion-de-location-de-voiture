package interfaces;

import java.util.List;

import model.Client;

public interface IClient {
	public Client findOne(Long id);
	public List<Client> findAll();
	public Client create(Client client);
	public Client update(Client client);
	public void delete(Long id);
}
