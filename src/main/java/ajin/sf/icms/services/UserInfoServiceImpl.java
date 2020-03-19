package ajin.sf.icms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ajin.sf.icms.commons.ConvertPrintstackTrace;
import ajin.sf.icms.models.UserInfo;
import ajin.sf.icms.repositorys.UserInfoRepository;
import ajin.sf.icms.commons.ReturnCode;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	private ConvertPrintstackTrace convertTrace = new ConvertPrintstackTrace();
	
	@Autowired
	private UserInfoRepository userInfoRep;
	
	@Override
	public Integer Login(String userId, String userPw) throws Exception {
		
		List<Object[]> userInfoList = new ArrayList<Object[]>();
		Integer returnCode = ReturnCode.SERVER_ERROR;
		
		try {
			// id 있는지 체크
			boolean bChk = userInfoRep.existsByUserId(userId);
			if(bChk) {
				// id 및 pw 체크
				
				bChk = userInfoRep.existsByUserPw(userPw);
				if(bChk) {
					//userInfoList = userInfoRep.findByUserIdAndUserPw(userId, userPw);
					returnCode = ReturnCode.OK;
				} else {

					returnCode = ReturnCode.PW_ERROR;
				}
			} else {
				returnCode = ReturnCode.ID_ERROR;
			}	
		} catch (Exception e) {
			String msg = convertTrace.getPrintstackTrace(e);
			logger.error(msg);
			
			returnCode =ReturnCode.SERVER_ERROR; 
		}
		
		return returnCode;
	}

	@Override
	public UserInfo getUserInfo(String userId, String userPw) throws Exception {
		
		List<Object[]> userInfoList = new ArrayList<Object[]>();
		UserInfo user = new UserInfo();
		
		user = userInfoRep.findByUserIdAndUserPw(userId, userPw);
		
		
		
		
		
		return user;
		
	}
	
	public Boolean isUserId(String userId) throws Exception {
		
		return userInfoRep.existsByUserId(userId);
	}
	
	public String getUserID(String userId) throws Exception {
		
		UserInfo user = new UserInfo(); 
		List<UserInfo> userList = userInfoRep.findByUserId(userId);
		if(StringUtils.isEmpty(user.getUserId())) {
			user.setUserId("NULL");
		}
		
		return user.getUserId();
	}
	
	

}
