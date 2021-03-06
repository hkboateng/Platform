package com.boateng.abankus.database.configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;


//@Configuration
public class CassandraClient {
	/**
	 * 
	 */
	public static final String HOSTNAME = "127.0.0.1";

	private Cluster cluster;
	
	private Session session;
	public void connect(){
		cluster = Cluster.builder().addContactPoint(HOSTNAME).build();
	/**
		Metadata metadata = cluster.getMetadata();
		
	    for ( Host host : metadata.getAllHosts() ) {
	         System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
	               host.getDatacenter(), host.getAddress(), host.getRack());
	      }	
	      **/
		cluster.connect();
	}
	
	public void close() {
		   session.close();
		   cluster.close();
		}
	
	public static void main(String[] args){
		CassandraClient client = new CassandraClient();
		client.connect();
		client.addEmail("hkboatengyahoo.com");
		client.close();
		
	}
	
	public void addEmail(String email){
		session = cluster.connect();
		for(int i = 20000; i< 20000000;i++){
			session.execute("Insert into platformGH.email (emailId,emailAddress,emailType) values ("+i+",'hkboateng@yahoo.com','primary')");
		}
		
	}
}
