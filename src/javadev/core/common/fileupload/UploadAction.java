package javadev.core.common.fileupload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import javadev.iip.action.BaseAction;

/**
 * 处理所有角色的文件上传，根据UploadType类中定义的类型来进行分类
 * 
 * @author RenQiang
 * @version 1.0 Create Date: 2016-04-18
 */
public abstract class UploadAction extends BaseAction {
	protected File file;
	protected String fileFileName;
	protected String fileContentType;

	/**
	 * 功能描述：保存文件到服务器上的指定位置，即文件上传，继承本Action，只需要调用本方法就能上传文件并返回保存路径
	 * 
	 * @return 上传成功则返回相对于根目录的文件路径，例如：player/5200000090/520000009020160415142954.
	 *         jpg，也就是数据库中需要保存的数据
	 * @throws IOException
	 * @throws ExtensionsException
	 *             后缀名不允许异常
	 * @throws LengthException
	 *             文件过大异常
	 * @throws SizeException
	 *             图片尺寸不符合异常
	 * @throws NullFileParamException
	 *             没有接收到名为file的文件参数异常
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected String save()
			throws IOException, ExtensionsException, LengthException, SizeException, NullFileParamException {
		if (file == null) {
			throw new NullFileParamException("没有接收到名为file的文件参数");
		}

		// 验证
		validateExtensions();
		validateLength();
		validateSize();

		String rootPath = getRootPath();
		String savePath = getFileSavePath();
		String fileName = getFileSaveName();
		File saveFile = new File(rootPath + savePath, fileName);
		if (!saveFile.getParentFile().exists())
			saveFile.getParentFile().mkdirs();

		FileUtils.copyFile(file, saveFile);
		return savePath + "/" + fileName;
	}

	/**
	 * 功能描述：获得文件保存名
	 * 
	 * @return 文件名，例如：520000009020160415142954.jpg，ID+日期或者ID+flag或者ID+badge
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected String getFileSaveName() {
		// TODO Auto-generated method stub
		UploadType type = getUploadType();
		StringBuffer buffer = new StringBuffer();
		buffer.append(getID());
//		if (type == UploadType.Team_Badge) {
//			buffer.append("badge");
//		}
//
//		if (type == UploadType.Team_flag) {
//			buffer.append("flag");
//		}

		if (!type.getRole().contains("Team")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			buffer.append(sdf.format(new Date()));
		}

		if (type.getRole().contains("Team")) {
			buffer.append(".jpg");
		} else {
			buffer.append("." + getFileExtension());
		}
		
		return buffer.toString();
	}

	/**
	 * 功能描述：获得文件保存在服务器上的路径
	 * 
	 * @return 相对于根目录的路径
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected String getFileSavePath() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getSecondPath());
		if (!getUploadType().getRole().contains("Team"))
			buffer.append("/" + getID());
		return buffer.toString();
	}

	/**
	 * 功能描述：获取当前文件附属角色ID，运动员ID、教练员ID、比赛现场ID、球队ID中的一种
	 * 
	 * @return ID
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected abstract String getID();

	/**
	 * 功能描述：获取不同上传类型的文件保存二级目录
	 * 
	 * @return 二级目录
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected String getSecondPath() {
		UploadType type = getUploadType();
		String role = null;
		// 运动员文件保存在player文件夹下
		if (type.getRole().contains("Player")) {
			role = "player";
		}

		// 教练员文件保存在coach文件夹下
		if (type.getRole().contains("Coach")) {
			role = "coach";
		}

		// 比赛直播文件保存在game文件夹下
		if (type.getRole().contains("Game")) {
			role = "game";
		}

		// 队旗保存在flag文件夹下
		if (type == UploadType.Team_flag) {
			role = "flag";
		}

		// 队徽保存在badge文件夹下
		if (type == UploadType.Team_Badge) {
			role = "badge";
		}

		return role;
	}

	/**
	 * 功能描述：获取不同上传类型的文件保存根地址
	 * 
	 * @return 跟地址
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected String getRootPath() {
		UploadType type = getUploadType();
		String rootPath = null;
		// 图片保存跟地址，这里会包含队旗和队徽，但是会被后面覆盖
		if (type.getRole().contains("Photo")) {
			rootPath = getServMgr().getConfigureService().get("pic_root");
		}

		// 视频保存跟地址
		if (type.getRole().contains("Video")) {
			rootPath = getServMgr().getConfigureService().get("video_root");
		}

		// 队旗队徽保存跟地址
		if (type.getRole().contains("Team")) {
			rootPath = getServMgr().getConfigureService().get("pic_root");
		}

		return rootPath;
	}

	/**
	 * 功能描述：验证文件大小是否允许上传
	 * 
	 * @return 验证通过返回true
	 * @throws LengthException
	 *             验证不通过抛出异常
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected boolean validateLength() throws LengthException {
		UploadType type = getUploadType();
		long maxLength = 0;
		long length = file.length()/1024;
		// 证件照50KB
		if (type.getRole().contains("ID")) {
			maxLength = 50;
		}

		// 队旗队徽500KB
		if (type.getRole().contains("Team")) {
			maxLength = 500;
		}

		// 精彩照片500KB
		if (type.getRole().contains("Wonderful")) {
			maxLength = 500;
		}

		// 精彩视频10MB
		if (type.getRole().contains("Video")) {
			maxLength = 10 * 1024;
		}

		if (length > maxLength) {
			throw new LengthException("文件过大，当前" + length + "KB，不能超过" + maxLength + "KB");
		}

		return true;
	}

	/**
	 * 功能描述：验证图片尺寸是否允许上传，详细规则待定，现在只是测试
	 * 
	 * @return 验证通过返回true
	 * @throws SizeException
	 *             验证不通过抛出异常
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected boolean validateSize() throws SizeException {
		UploadType type = getUploadType();
		if (type.getRole().contains("Photo")) {
			try {
				BufferedImage bi = ImageIO.read(file);
				int width = bi.getWidth();
				int height = bi.getHeight();
				if (width > 1000 || height > 1000) {
					throw new SizeException("图片尺寸过大");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return true;
	}

	/**
	 * 功能描述：验证后缀名是否允许上传
	 * 
	 * @return 验证通过返回true
	 * @throws ExtensionsException
	 *             验证不通过抛出异常
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected boolean validateExtensions() throws ExtensionsException {
		List<String> extensions = getExtensions();
		String currentExtension = getFileExtension();
		if (extensions.contains(currentExtension))
			return true;

		throw new ExtensionsException("文件类型不予许，" + currentExtension + "not in" + extensions);
	}

	/**
	 * 功能描述：获取当前文件的后缀名
	 * 
	 * @return 文件后缀名
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected String getFileExtension() {
		return fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toLowerCase();
	}

	/**
	 * 功能描述：获取上传文件类型，为UploadType中定义的某种类型
	 * 
	 * @return 上传类型
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected abstract UploadType getUploadType();

	/**
	 * 功能描述：获取当前上传类型允许的后缀名集合
	 * 
	 * @return 后缀名集合
	 * @author RenQiang Create Date: 2016-04-18
	 */
	protected List<String> getExtensions() {
		List<String> result = new ArrayList<>();
		UploadType type = getUploadType();
		// 图片格式限制
		if (type.getRole().contains("Photo")) {
			result.add("bmp");
			result.add("jpg");
			result.add("jpeg");
			result.add("png");
		}

		// 视频格式限制
		if (type.getRole().contains("Video")) {
			result.add("wmv");
		}

		return result;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
}
