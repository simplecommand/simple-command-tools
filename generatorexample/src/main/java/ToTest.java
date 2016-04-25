import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "test")
public class ToTest {

  @Id
  public int id;

  @Basic
  public String data;
}