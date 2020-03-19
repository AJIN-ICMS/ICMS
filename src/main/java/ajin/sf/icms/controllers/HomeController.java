package ajin.sf.icms.controllers;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ajin.sf.icms.commons.ConvertPrintstackTrace;
import ajin.sf.icms.commons.Version;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private ConvertPrintstackTrace convertTrace = new ConvertPrintstackTrace();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void serverVersion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Version version = Version.getInst();
		String avisBrzVersion = version.getVersion();
		byte[] msgByte = avisBrzVersion.getBytes(); 
		OutputStream os = response.getOutputStream();
		
		try {
			os.write(msgByte);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e ) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch(Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				
				String msg = convertTrace.getPrintstackTrace(e);
				logger.error(msg);
			}
		}
	}
	
	
	
}
