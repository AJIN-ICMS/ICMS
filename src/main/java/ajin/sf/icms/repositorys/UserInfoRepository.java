package ajin.sf.icms.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajin.sf.icms.models.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	List<UserInfo> findByUserId(String userId);
	
	boolean existsByUserId(String userId);
	boolean existsByUserPw(String userPw);

	UserInfo findByUserIdAndUserPw(String userId, String userPw);
	
	
}

