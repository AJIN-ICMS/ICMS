package ajin.sf.icms.services;

import ajin.sf.icms.models.UserInfo;

public interface UserInfoService {
	
	public Integer Login(String userId, String userPw) throws Exception;
	public UserInfo getUserInfo(String userId, String userPw) throws Exception;
	
	public Boolean isUserId(String userId) throws Exception;
	public String getUserID(String userId) throws Exception;
}
