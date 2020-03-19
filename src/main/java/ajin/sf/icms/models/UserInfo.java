package ajin.sf.icms.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_INFO")
public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4500822910274710914L;

	@Id
	@Column(name = "user_id", columnDefinition = "VARCHAR(20)")
	private String userId;
	
	@Column(name = "user_pw", columnDefinition = "VARCHAR(20)")
	private String userPw;
	
	@Column(name = "user_team", columnDefinition = "VARCHAR(20)")
	private String userTeam;
	
	@Column(name = "user_contact", columnDefinition = "VARCHAR(20)")
	private String userContact;
	
	@Column(name = "user_role", columnDefinition = "VARCHAR(20)")
	private String userRole;
	
	@Column(name = "user_joined_time", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userJoinedTime = new Date(System.currentTimeMillis());

	public UserInfo() {
		
	}
}
