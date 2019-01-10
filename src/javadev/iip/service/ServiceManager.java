package javadev.iip.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javadev.iip.service.art.ArtEndService;
import javadev.iip.service.art.ArtFirstService;
import javadev.iip.service.art.ArtFourthService;
import javadev.iip.service.art.ArtSecondService;
import javadev.iip.service.art.ArtSomeService;
import javadev.iip.service.art.ArtThirdService;
import javadev.iip.service.configure.ConfigureService;
import javadev.iip.service.sport.SportEndServive;
import javadev.iip.service.sport.SportFirstService;
import javadev.iip.service.sport.SportReportBySelfService;
import javadev.iip.service.sport.SportSecondService;
import javadev.iip.service.sport.SportServiceTest;
import javadev.iip.service.sport.SportThirdService;
import javadev.iip.service.test.RepostFormService;

public class ServiceManager {
	protected final Log log = LogFactory.getLog(getClass());

	/* 项目所用到的 Service */
	private UserService userService;
	private ConfigureService configureService;
	private RepostFormService repostFormService;
	
	private OtherService otherService;
	
	

	private	ArtFirstService artFirstService;
	private	ArtSecondService artSecondService;
	private	ArtThirdService artThirdService;
	private	ArtFourthService  artFourthService;
	private ArtSomeService artSomeService;
	private ArtEndService artEndService;
	
	
	private IsPassService isPassService;

	public ArtEndService getArtEndService() {
		return artEndService;
	}

	public void setArtEndService(ArtEndService artEndService) {
		this.artEndService = artEndService;
	}

	private SportFirstService sportFirstService;
	private SportSecondService sportSecondService;
	private SportThirdService sportThirdService;
	private SportEndServive sportEndServive;
	
	public SportEndServive getSportEndServive() {
		return sportEndServive;
	}

	public void setSportEndServive(SportEndServive sportEndServive) {
		this.sportEndServive = sportEndServive;
	}

	private  SportServiceTest sportServiceTest;
	
	private SportReportBySelfService sportReportBySelfService;


	public SportReportBySelfService getSportReportBySelfService() {
		return sportReportBySelfService;
	}

	public void setSportReportBySelfService(SportReportBySelfService sportReportBySelfService) {
		this.sportReportBySelfService = sportReportBySelfService;
	}

	public SportServiceTest getSportServiceTest() {
		return sportServiceTest;
	}

	public void setSportServiceTest(SportServiceTest sportServiceTest) {
		this.sportServiceTest = sportServiceTest;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ConfigureService getConfigureService() {
		return configureService;
	}

	public void setConfigureService(ConfigureService configureService) {
		this.configureService = configureService;
	}

	public RepostFormService getRepostFormService() {
		return repostFormService;
	}

	public void setRepostFormService(RepostFormService repostFormService) {
		this.repostFormService = repostFormService;
	}

	public ArtFirstService getArtFirstService() {
		return artFirstService;
	}

	public void setArtFirstService(ArtFirstService artFirstService) {
		this.artFirstService = artFirstService;
	}

	public ArtSecondService getArtSecondService() {
		return artSecondService;
	}

	public void setArtSecondService(ArtSecondService artSecondService) {
		this.artSecondService = artSecondService;
	}

	public ArtThirdService getArtThirdService() {
		return artThirdService;
	}

	public void setArtThirdService(ArtThirdService artThirdService) {
		this.artThirdService = artThirdService;
	}

	public ArtFourthService getArtFourthService() {
		return artFourthService;
	}

	public void setArtFourthService(ArtFourthService artFourthService) {
		this.artFourthService = artFourthService;
	}

	public SportFirstService getSportFirstService() {
		return sportFirstService;
	}

	public void setSportFirstService(SportFirstService sportFirstService) {
		this.sportFirstService = sportFirstService;
	}

	public SportSecondService getSportSecondService() {
		return sportSecondService;
	}

	public void setSportSecondService(SportSecondService sportSecondService) {
		this.sportSecondService = sportSecondService;
	}

	public SportThirdService getSportThirdService() {
		return sportThirdService;
	}

	public void setSportThirdService(SportThirdService sportThirdService) {
		this.sportThirdService = sportThirdService;
	}

	public OtherService getOtherService() {
		return otherService;
	}

	public void setOtherService(OtherService otherService) {
		this.otherService = otherService;
	}

	public ArtSomeService getArtSomeService() {
		return artSomeService;
	}

	public void setArtSomeService(ArtSomeService artSomeService) {
		this.artSomeService = artSomeService;
	}

	public IsPassService getIsPassService() {
		return isPassService;
	}

	public void setIsPassService(IsPassService isPassService) {
		this.isPassService = isPassService;
	}
	
	


	
	
	
}
