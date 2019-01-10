package javadev.iip.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javadev.iip.service.configure.ConfigureService;
import javadev.iip.service.fiveplan.AuditStatusService;
import javadev.iip.service.fiveplan.DownLoadExcelService;
import javadev.iip.service.fiveplan.FileService;
import javadev.iip.service.fiveplan.FivePlanLoginService;
import javadev.iip.service.fiveplan.FivePlanModifyPDService;
import javadev.iip.service.fiveplan.PageOneService;
import javadev.iip.service.fiveplan.PhyMusicDrawTeacherService;
import javadev.iip.service.fiveplan.RespostFromActionTwoService;
import javadev.iip.service.fiveplan.SportsArtTeachEquipService;
import javadev.iip.service.test.RepostFormService;

public class ServiceManager {
	protected final Log log = LogFactory.getLog(getClass());

	/* 项目所用到的 Service */
private UserService userService;
	private ConfigureService configureService;
	private RepostFormService repostFormService;
	private FivePlanLoginService fivePlanLoginService;
	private RespostFromActionTwoService respostFromActionTwoService;
	private SportsArtTeachEquipService sportsArtTeachEquipService;
	private FivePlanModifyPDService fivePlanModifyPDService;
	private PageOneService pageOneService;
	private DownLoadExcelService downLoadExcelService;
	private FileService fileService;
	private PhyMusicDrawTeacherService phyMusicDrawService; 
	private AuditStatusService auditStatusService;
	private IsPassService isPassService;
	
	public IsPassService getIsPassService() {
		return isPassService;
	}

	public void setIsPassService(IsPassService isPassService) {
		this.isPassService = isPassService;
	}

	public DownLoadExcelService getDownLoadExcelService() {
		return downLoadExcelService;
	}

	public void setDownLoadExcelService(DownLoadExcelService downLoadExcelService) {
		this.downLoadExcelService = downLoadExcelService;
	}

	public PageOneService getPageOneService() {
		return pageOneService;
	}

	public void setPageOneService(PageOneService pageOneService) {
		this.pageOneService = pageOneService;
	}

	public SportsArtTeachEquipService getSportsArtTeachEquipService() {
		return sportsArtTeachEquipService;
	}

	public void setSportsArtTeachEquipService(SportsArtTeachEquipService sportsArtTeachEquipService) {
		this.sportsArtTeachEquipService = sportsArtTeachEquipService;
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

	public FivePlanLoginService getFivePlanLoginService() {
		return fivePlanLoginService;
	}

	public void setFivePlanLoginService(FivePlanLoginService fivePlanLoginService) {
		this.fivePlanLoginService = fivePlanLoginService;
	}

	public RespostFromActionTwoService getRespostFromActionTwoService() {
		return respostFromActionTwoService;
	}

	public void setRespostFromActionTwoService(RespostFromActionTwoService respostFromActionTwoService) {
		this.respostFromActionTwoService = respostFromActionTwoService;
	}

	public FivePlanModifyPDService getFivePlanModifyPDService() {
		return fivePlanModifyPDService;
	}

	public void setFivePlanModifyPDService(FivePlanModifyPDService fivePlanModifyPDService) {
		this.fivePlanModifyPDService = fivePlanModifyPDService;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public PhyMusicDrawTeacherService getPhyMusicDrawService() {
		return phyMusicDrawService;
	}

	public void setPhyMusicDrawService(PhyMusicDrawTeacherService phyMusicDrawService) {
		this.phyMusicDrawService = phyMusicDrawService;
	}

	public AuditStatusService getAuditStatusService() {
		return auditStatusService;
	}

	public void setAuditStatusService(AuditStatusService auditStatusService) {
		this.auditStatusService = auditStatusService;
	}

    
	
    
}
