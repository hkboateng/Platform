
public class ClientCustomerDAO {
    
    private DataSource dataSource;
    
    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource ds){
      this.dataSource = ds;
      this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Customer saveCustomer(Customer customer){
    
    }
}
