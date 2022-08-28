package PostBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sun.rowset.CachedRowSetImpl;
import PostBar.ImageUtil;

public class ImageGet {
    private  ResultSet resultSet=null;
    private  InputStream in = null;
    private FileOutputStream out=null;
  
	public ImageGet(CachedRowSetImpl resultSet,String filepath) {
		this.resultSet=resultSet;
		int i=1;
		try {  
			while(resultSet.next()){
				in = resultSet.getBinaryStream(3);
				ImageUtil.readBlob(in, filepath+i+".jpg");
				i++;
			}
	    } catch (SQLException e) {
	         e.printStackTrace();
	    }
    }

	public void Rsclose(){
        try {
           if (resultSet != null)
              resultSet.close();
         } catch (SQLException e) {
              e.printStackTrace();
         } 
	}
	
}
