



import java.io.*;
import java.util.List;

/**
 * 寤鸿锛氭渶濂藉皢闇�瑕佽浆鎹㈢殑鏂囦欢澶�/鏂囦欢澶嶅埗涓�浠斤紝浠ュ厤鍑虹幇缂栫爜杞崲鐨勬剰澶�
 * 娉ㄦ剰锛氫笉瑕佽繘琛屼簩娆¤浆鎹紝杩樻湁涓�瀹氳纭畾琚浆鎹㈢殑鏂囦欢鏄疓BK鏍煎紡鐨勩�愪竴鑸紝濡傛灉eclipse鐨勬枃浠舵斁鍒癷dea涓贡鐮侊紝澶氬崐鏄疓BK鏍煎紡鐨勩��
 */
public class Converter {
// 澶嶅埗瀹屽叏涓�鏍风殑鏂囦欢锛屽寘鍚枃浠跺す閲岄潰鐨勬墍鏈夊唴瀹广�愮暐--鎵嬪姩澶勭悊銆�

    public static void main(String[] args) throws IOException {
            new Converter().converter(new File("C:\\Users\\clt\\Desktop\\2-3閮鐓岃鍗曠鐞嗙郴缁燂紙閲嶅彂锛�"));
    }


    public void converter(File file) throws IOException {
        convertEclipseFiles(file); // 銆愬繀鏀癸紝鏂囦欢鐨勮矾寰勩��
        System.out.println("杞崲鎴愬姛锛�");
    }

    public  void convertEclipseFiles(File file) throws IOException {
        if (!file.exists()) {
            throw new RuntimeException("姝ゆ枃浠舵垨鏂囦欢澶逛笉瀛樺湪");
        }
        if (file.isFile() && file.getName().endsWith(".java")) { //銆愬彲鏀癸紝灏唀clipse閲岄潰鐨凧ava鏂囦欢缂栫爜鏀规垚utf-8銆�
            //!! 閲嶅啓鏂囦欢锛屾渶鍚庡垹闄ゅ師鏉ョ殑鏂囦欢
            String absolutePath = file.getAbsolutePath();
            File file1 = new File(absolutePath.substring(0, absolutePath.length() - 5) + "_copy" + ".java");
            convertEncoding(file,file1);
            file.delete();
            file1.renameTo(new File(absolutePath));
        }
        if (!file.isFile()) {
            File[] fs = file.listFiles();// 鑾峰彇褰撳墠鏂囦欢澶逛笅鐨勫瓙鏂囦欢澶规垨鑰呮枃浠剁殑file瀵硅薄
            if (fs != null && fs.length > 0) {
                for (File ff : fs) {
                    convertEclipseFiles(ff);// 閫掑綊
                }
            }
        }
    }

    /*
     * 鐩爣锛氭妸1.txt鍐呭澶嶅埗鍒�2.txt
     */
    public void convertEncoding(File oldFile, File newFile) throws IOException {
        FileInputStream fis = new FileInputStream(oldFile);
        FileOutputStream fos = new FileOutputStream(newFile);
        byte[] content = new byte[1024];
        int read = fis.read(content);
        while (read != -1) {
//            System.out.println(new String(content, 0, read, "GBK")); // 鏌ョ湅read鐨勭粨鏋�
            fos.write(new String(content, 0, read, "GBK").getBytes("utf-8"));
            read = fis.read(content);
        }
    fis.close();
    fos.close();
    }
}
