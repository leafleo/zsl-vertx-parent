package org.zsl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 工具类
 * 项目名称：vertx_web    
 * 类名称：S    
 * 类描述：    
 * 创建人：liujunqing    
 * 创建时间：2015年7月18日  
 * @version 1.0    
 *
 */
public class S<T> {
	private static final Logger logger = LoggerFactory.getLogger(S.class);
	
	public static <T> T avoidNull(T _check, T _else) {
        return _check == null ? _else : _check;
    }
	
	public static class file {

        /**
         * (Util method)
         * Load properties from the file
         */
        public static Properties loadProperties(File conf) {
                Properties config = new Properties();
                if (conf.exists() && conf.canRead())
					try {
						config.load(new FileInputStream(conf));
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("Can`t read properties file");
						throw new RuntimeException(e);
					}
				else
                   logger.error(
                            "Can`t read properties file, using default.");
                return config;
        }

        /**
         * (Util method)
         * Load properties from the file, under the classroot
         */
        public static Properties loadProperties(String fileName) {
        		logger.debug("load paroperties fileName:"+fileName);
                Properties config = new Properties();
                File conf = new File(S.path.rootClassPath()
                        + File.separator + fileName);
                if (conf.exists() && conf.canRead())
					try {
						config.load(new FileInputStream(conf));
					} catch (IOException e) {
						logger.error(
		                            "Can`t read properties file, using default.");
						 
					}
				else
                   logger.error(
                            "Can`t read properties file, using default.");
                return config;
        }

        public static void inputStreamToFile(InputStream ins, File file) throws IOException {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        }

        public static String fileNameFromPath(String path) {
            return path.substring(path.lastIndexOf("\\") + 1);
        }

        /**
         * Returns file extension name,
         * return null if it has no extension.
         *
         * @param fileName
         * @return
         */
        public static String fileExt(String fileName) {
            String[] filename = splitFileName(fileName);
            return filename[filename.length - 1];
        }

        public static String[] splitFileName(String filename) {
            int idx_dot = filename.lastIndexOf('.');
            if (idx_dot <= 0 || idx_dot == filename.length()) {
                return new String[]{filename, null};
            }
            return new String[]{filename.substring(0, idx_dot), filename.substring(idx_dot + 1)};
        }

        /**
         * abc.txt => [abc, txt] abc.def.txt => [abc.def, txt] abc. =>
         * [abc.,null] .abc => [.abc,null] abc => [abc,null]
         *
         * @param file file
         * @return string array with size of 2, first is the filename, remain the suffix;
         */
        public static String[] splitFileName(File file) {
            return splitFileName(file.getName());
        }

        public static File mkdir(File par, String name) throws IOException {
            final String path = par.getAbsolutePath() + File.separatorChar + name;
            File f = new File(path);
            if (f.mkdirs() && f.createNewFile()) {
                return f;
            }
            return null;
        }

        public static File touch(File par, String name) throws IOException {
            final String path = par.getAbsolutePath() + File.separatorChar + name;
            File f = new File(path);
            if (f.createNewFile()) {
                return f;
            }
            return null;
        }

        /**
         * Delete a dir recursively deleting anything inside it.
         *
         * @param file The dir to delete
         * @return true if the dir was successfully deleted
         */
        public static boolean rm(File file) {
            if (!file.exists() || !file.isDirectory()) {
                return false;
            }

            String[] files = file.list();
            for (String file1 : files) {
                File f = new File(file, file1);
                if (f.isDirectory()) {
                    rm(f);
                } else {
                    f.delete();
                }
            }
            return file.delete();
        }
    }


	/**
	 * 查询路径类
	 * 创建时间：2015年7月18日  
	 */
	public static class path{
		/**
	     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
	     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
	     */
	    public static String getRootPath(URL url) {
	        String fileUrl = url.getFile();
	        int pos = fileUrl.indexOf('!');
	 
	        if (-1 == pos) {
	            return fileUrl;
	        }
	 
	        return fileUrl.substring(5, pos);
	    }
	 
	    /**
	     * "zsl.org.core" -> "zsl/org/core"
	     * @param name
	     * @return
	     */
	    public static String dotToSplash(String name) {
	        return name.replaceAll("\\.", "/");
	    }
	 
	    /**
	     * "Apple.class" -> "Apple"
	     */
	    public static String trimExtension(String name) {
	        int pos = name.indexOf('.');
	        if (-1 != pos) {
	            return name.substring(0, pos);
	        }
	 
	        return name;
	    }
	 
	    /**
	     * /application/home -> /home
	     * @param uri
	     * @return
	     */
	    public static String trimURI(String uri) {
	        String trimmed = uri.substring(1);
	        int splashIndex = trimmed.indexOf('/');
	        return trimmed.substring(splashIndex);
	    }
		
		/**
		 * 获得当前类路径下的的根路径
		 *@param caller
		 *@return
		 */
        public static String rootAbsPath(Object caller) {
            return caller.getClass().getClassLoader().getResource("/").getPath();
        }

        /**
		 * 获得当前类路径下的的根路径
		 *@param caller
		 *@return
		 */
        public static String rootAbsPath(Class<?> callerClass) {
            return callerClass.getClassLoader().getResource("/").getPath();
        }

        
        public static String get(Class<?> clazz) {
            String path = clazz.getResource("").getPath();
            return new File(path).getAbsolutePath();
        }

        public static String get(Object object) {
            String path = object.getClass().getResource("").getPath();
            return new File(path).getAbsolutePath();
        }

        public static String rootClassPath() {
            try {
                String path = S.class.getClassLoader().getResource("").toURI().getPath();
                return new File(path).getAbsolutePath();
            } catch (URISyntaxException e) {
                String path = S.class.getClassLoader().getResource("").getPath();
                return new File(path).getAbsolutePath();
            }
        }
        /**
         * 获得当前包名
         *@param object
         *@return
         */
        public static String packageOf(Object object) {
            Package p = object.getClass().getPackage();
            return p != null ? p.getName().replaceAll("\\.", "/") : "";
        }
        /**
         * 获得webRoot的路径
         *@return
         */
        public static String detectWebRootPath() {
            try {
                String path = S.class.getResource("/").toURI().getPath();
                return new File(path).getParentFile().getParentFile().getCanonicalPath();
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        /**
         * 判断是否绝对路径
         *@param path
         *@return
         */
        public static Boolean isAbsolute(String path) {
            return path.startsWith("/") ||
                    path.indexOf(":") == 1;
        }
	}



}
