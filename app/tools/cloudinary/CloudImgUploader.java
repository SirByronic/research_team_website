package tools.cloudinary;

import com.cloudinary.Cloudinary;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by aazzou on 10/20/14.
 */
public class CloudImgUploader {
    private Map uploadImgResult;


    public CloudImgUploader(File img,Map params) {
        Cloudinary cloudinary = new Cloudinary(Cloudinary.asMap(
                "cloud_name", CloudUploaderParams.CLOUD_NAME,
                "api_key", CloudUploaderParams.API_KEY,
                "api_secret", CloudUploaderParams.API_SECRET));
        try {
             uploadImgResult = cloudinary.uploader().upload(img,params);
        }catch(IOException e){
            uploadImgResult = null;
        }
    }

    public String url(){
        if(uploadImgResult != null){
            return uploadImgResult.get("url").toString();
        }else return null;
    }

    public String publicID(){
        if(uploadImgResult != null){
            return uploadImgResult.get("public_id").toString();
        }else return null;
    }


}
