package hmfms.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 文件操作实用类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:56:36</p>
 * @author 产品开发部
 * @version 2.0
 * FileUtil
 */
public class FileUtil extends AbstractUtil {
	private static final Log logger = LogFactory.getLog(FileUtil.class);
	
	private static final int DEFAULT_CHUNK_SIZE = 1024;
	public static final char FILESYSTEM_SEPARATOR = File.separatorChar;

	public static long genFileNumber() {
		return System.currentTimeMillis();
	}

	/**
	 * save bytes to file
	 * 
	 * @param fileName
	 *            the file to write the supplied bytes
	 * @param theBytes
	 *            the bytes to write to file
	 * @throws IOException
	 *             reports problems saving bytes to file
	 */
	public static void saveBytesToFile(String fileName, byte[] theBytes) throws IOException {

		saveBytesToStream(new FileOutputStream(fileName), theBytes);
	}

	/**
	 * save bytes to output stream and close the output stream on success and on
	 * failure.
	 * 
	 * @param out
	 *            the output stream to write the supplied bytes
	 * @param theBytes
	 *            the bytes to write out
	 * @throws IOException
	 *             reports problems saving bytes to output stream
	 */
	public static void saveBytesToStream(OutputStream out, byte[] theBytes) throws IOException {

		try {
			out.write(theBytes);
		}
		finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * Loads bytes from the file
	 * 
	 * @param fileName
	 *            file to read the bytes from
	 * @return bytes read from the file
	 * @exception IOException
	 *                reports IO failures
	 */
	public static byte[] loadBytesFromFile(String fileName) throws IOException {

		return loadBytesFromStream(new FileInputStream(fileName), DEFAULT_CHUNK_SIZE);
	}

	/**
	 * Loads bytes from the given input stream until the end of stream is
	 * reached. It reads in at kDEFAULT_CHUNK_SIZE chunks.
	 * 
	 * @param stream
	 *            to read the bytes from
	 * @return bytes read from the stream
	 * @exception IOException
	 *                reports IO failures
	 */
	public static byte[] loadBytesFromStream(InputStream in) throws IOException {

		return loadBytesFromStream(in, DEFAULT_CHUNK_SIZE);
	}

	/**
	 * Loads bytes from the given input stream until the end of stream is
	 * reached. Bytes are read in at the supplied <code>chunkSize</code> rate.
	 * 
	 * @param stream
	 *            to read the bytes from
	 * @return bytes read from the stream
	 * @exception IOException
	 *                reports IO failures
	 */
	public static byte[] loadBytesFromStream(InputStream in, int chunkSize) throws IOException {

		if( chunkSize < 1 )
			chunkSize = DEFAULT_CHUNK_SIZE;

		int count;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] b = new byte[chunkSize];
		try {
			while( (count = in.read(b, 0, chunkSize)) > 0 ) {
				bo.write(b, 0, count);
			}
			byte[] thebytes = bo.toByteArray();
			return thebytes;
		}
		finally {
			bo.close();
		}
	}

	/**
	 * 从文件流读取文件内容
	 * @param reader 文件字符流
	 * @return
	 */
	public static String ReadFile(Reader reader) {

		StringBuffer buf = null;
		Reader fr = null;

		try {
			fr = reader;

			int theChar;
			buf = new StringBuffer();

			while( ((theChar = fr.read()) != -1) ) {
				buf.append((char)theChar);
			}
		}
		catch(IOException ioe) {
			Debug.exception(logger,ioe);
		}
		finally {
			if( fr != null ) {
				try {
					fr.close();
				}
				catch(IOException ioe) {}
			}
		}

		if( buf != null )
			return buf.toString();
		else
			return "";
	}

	/**
	 * 从指定路径文件读取文件内容
	 * @param file 文件绝对路径
	 * @return
	 */
	public static String ReadFile(String file) {

		StringBuffer buf = null;
		FileReader fr = null;

		try {
			fr = new FileReader(file);
			int theChar;
			buf = new StringBuffer();

			while( ((theChar = fr.read()) != -1) ) {
				buf.append((char)theChar);
			}
		}
		catch(IOException ioe) {
			Debug.info(logger,file);
			Debug.exception(logger,ioe);
		}
		finally {
			if( fr != null ) {
				try {
					fr.close();
				}
				catch(IOException ioe) {}
			}
		}

		if( buf != null )
			return buf.toString();
		else
			return "";
	}

	/**
	 * 将指定内容写到文件,如已有同名文件会覆盖
	 * @param fileName 文件绝对路径
	 * @param content 要写到文件的内容
	 */
	public static void WriteFile(String fileName, String content) {

		FileWriter fw = null;
		PrintWriter out = null;
		try {
			File f = new File(fileName);

			f.delete();
			fw = new FileWriter(fileName, true);
			out = new PrintWriter(fw);
			out.println(content);
			//Debug.info(logger,"Generated java file ["+fileName+"] is
			// successed!");
		}
		catch(IOException ioe) {
			Debug.exception(logger,ioe);
		}
		finally {
			if( out != null ) {
				try {
					out.close();
				}
				catch(Exception ioe) {}
			}
		}
	}

	/**
	 * 将指定内容写到文件,如已有同名文件会覆盖
	 * @param fileName 文件绝对路径
	 * @param content 要写到文件的内容
	 */
	public static void WriteFile(String fileName, byte []content) {

		FileOutputStream fos = null;
		try {
			File f = new File(fileName);

			f.delete();
			fos = new FileOutputStream(fileName, true);
			fos.write(content);
			fos.flush();
			//Debug.info(logger,"Generated java file ["+fileName+"] is
			// successed!");
		}
		catch(IOException ioe) {
			Debug.exception(logger,ioe);
		}
		finally {
			if( fos != null ) {
				try {
					fos.close();
				}
				catch(Exception ioe) {}
			}
		}
	}

	/**
	 * 将指定内容写到文件,如已有同名文件则将内容追加到文件末尾
	 * @param fileName 文件绝对路径
	 * @param content 要写到文件的内容
	 */
	public static void SaveWriteFile(String fileName, String content) {

		FileWriter fw = null;
		PrintWriter out = null;
		try {
			//File f = new File(fileName);
			fw = new FileWriter(fileName, true);
			out = new PrintWriter(fw);
			out.println(content);
			//Debug.info(logger,"Generated java file ["+fileName+"] is
			// successed!");
		}
		catch(IOException ioe) {
			Debug.exception(logger,ioe);
		}
		finally {
			if( out != null ) {
				try {
					out.close();
				}
				catch(Exception ioe) {}
			}
		}
	}

	/**
	 * 获取文件大小,单位:字节
	 * @param fileName 文件绝对路径
	 * @return
	 */
	public static long getFileSize(String fileName) {

		long len = 0;
		try {
			File f = new File(fileName);

			len = f.length();

		}
		catch(Exception e) {
			Debug.exception(logger,e);
		}
		finally {}
		return len;

	}

	/**
	 * 把文件的后缀名去掉后返回文件全名字
	 * @param filename_dot 文件全名
	 * @return
	 */
	public static String getFileName(String filename_dot) {

		return filename_dot.substring(0, filename_dot.lastIndexOf('.'));
	}

	/**
	 * 取目录下的文件名称列表（不包含目录名）
	 * @param dir
	 * @return
	 */
	public static List getFileListInDir(String dir) {

		return getFileListInDir(dir, false);
	}

	/**
	 * 取目录下的文件名称列表
	 * @param dir
	 * @param includeFolder 是否包含路径
	 * @return
	 */
	public static List getFileListInDir(String dir, boolean includeFolder) {

		List list = new ArrayList();
		File f = new File(dir);
		if( f.isDirectory() ) {
			File files[] = f.listFiles();
			for(int i = 0; i < files.length; i++) {
				String filePath = files[i].getAbsolutePath();
				if( files[i].isDirectory() ) {
					if( includeFolder )
						list.add(filePath);
				}
				else
					list.add(filePath);
			}
		}
		return list;
	}

	/**
	 * 取目录下的所有目录列表（不包含子目录）
	 * @param dir
	 * @param includeFolder
	 * @return
	 */
	public static List getFoldersListInDir(String dir) {

		List list = new ArrayList();
		File f = new File(dir);
		if( f.isDirectory() ) {
			File files[] = f.listFiles();
			for(int i = 0; i < files.length; i++) {
				String filePath = files[i].getAbsolutePath();
				if( files[i].isDirectory() ) {
					list.add(filePath);
				}
			}
		}
		return list;
	}

	/**
	 * 按包名建立文件夹
	 * @param basefolder 根目录
	 * @param packagepath 包名
	 * @return a String will all path of basefolder+packagepath
	 * @exception
	 */
	public static String createPackageFolder(String basefolder, String packagepath) throws Exception {

		File f = new File(basefolder);

		if( !f.isDirectory() ) {
			throw new Exception("根目录不存在！ " + basefolder);
		}
		List pf = StringUtil.parseStringToArrayList(packagepath, ".");

		String filesystemSeparate = f.getPath();
		if( filesystemSeparate.indexOf('/') > -1 )
			filesystemSeparate = "/";
		else if( filesystemSeparate.indexOf('\\') > -1 )
			filesystemSeparate = "\\";
		else
			throw new Exception("目录名非法！ " + basefolder);
		String newfolder = null;
		for(int i = 0; i < pf.size(); i++) {

			String p = (String)pf.get(i);

			newfolder = f.getPath() + filesystemSeparate + p;
			//Debug.info(logger,"----->folder:"+newfolder);
			File newp = new File(newfolder);
			if( !newp.isDirectory() ) {
				newp.mkdir();
			}
			f = new File(newfolder);
		}
		//Debug.info(logger,"fullpath :"+fullpath);
		return newfolder + filesystemSeparate;
	}

	/**
	 * 用当前系统分隔符替换文件路径内的分隔符
	 * @param filename
	 * @return
	 */
	public static String formatAbsoluteFilename(final String filename) {

		String tmpfilename = filename.replace('/', FILESYSTEM_SEPARATOR);
		return tmpfilename.replace('\\', FILESYSTEM_SEPARATOR);
	}

	public static String getFileExtName(String filename) {

		int fileDot = filename.lastIndexOf('.');
		String fileExName = null;
		if( fileDot != -1 ) {
			fileExName = filename.substring(fileDot, filename.length());
		}
		else
			fileExName = "";
		return fileExName;
	}

	/**
	 * 把一个文件移动到另外一个文件
	 * 
	 * @param srceFile 被转移的(源)文件（包括全路径）
	 * @param destFile 转移到的(目标)文件（包括全路径）
	 * @return int 0:表示被转移的文件不存在；1：表示转移成功；-1：表示转移失败
	 */
	public static int moveto(String srceFile, String destFile) {

		File oldFile = new File(srceFile);
		if( !oldFile.exists() )
			return 0;
		boolean ret = oldFile.renameTo(new File(destFile));
		if( ret )
			return 1;
		else
			return -1;
	}

	/**
	 * 删除一个文件
	 * 
	 * @param srceFile 被删除的文件（包括全路径）
	 * @return int 0:表示被删除的文件不存在；1：表示删除成功；-1：表示删除失败
	 */
	public static int delete(String srceFile) {

		File oldFile = new File(srceFile);
		if( !oldFile.exists() )
			return 0;
		boolean ret = oldFile.delete();
		if( ret )
			return 1;
		else
			return -1;
	}

	/**
	 * 删除文件或目录及目录下的所有内容
	 * @param file 文件或目录对象
	 */
	public static void deleteFiles(File file) {

		File f = file;
		if( f.isDirectory() ) {
			File files[] = f.listFiles();
			for(int i = 0; i < files.length; i++) {
				deleteFiles(files[i]);
			}
			f.delete();
		}
		else {
			f.delete();
		}
	}

	/**
	 * 建立目录
	 * @param folder 被建立的目录（包括全路径）
	 * @return int 1：表示建立成功；-1：表示建立失败
	 */
	public static int mkdir(String folder) {

		File oldFile = new File(folder);
		if( !oldFile.exists() ) {
			if( oldFile.mkdirs() )
				return 1;
			else
				return -1;
		}
		else
			return 1;
	}
	
	/**
	 * 往文件末尾追加内容
	 * @param fileName
	 * @param conent
	 */
	public static void fileAppend(String fileName, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName, true)));
			out.write(conent);
			
		} 
		catch (Exception e) {
			Debug.exception(logger,e);
		} 
		finally {
			try {
				out.close();
			} catch (IOException e) {
				Debug.exception(logger,e);
			}
		}
	}
	
	public static void main(String[] args) {

		WriteFile("D:/1.txt", "dsf");
		//Debug.info(logger,moveto("d:\\tmp\\SystemOut11.log", "d:\\tmp\\SystemOut1.log"));
		//Debug.info(logger,delete("d:\\tmp\\SystemOut11.log"));
	}
}
