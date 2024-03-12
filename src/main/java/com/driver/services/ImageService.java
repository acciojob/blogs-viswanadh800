package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image obj=new Image(description,dimensions);
        Blog blog=blogRepository2.findById(blogId).orElseGet(null);
        if(blog==null)
            return null;
        obj.setBlog(blog);
        List<Image> arr=blog.getImageList();
        arr.add(obj);
        blog.setImageList(arr);
        blogRepository2.save(blog);
        return obj;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image img=imageRepository2.findById(id).orElse(null);
        if(img==null)
            return 0;
        String  arr[]=(img.getDimensions().split("X",2));
        String  arr2[]=(screenDimensions.split("X",2));
        int c=(int)(Math.floor((double)Integer.parseInt(arr2[0])/(double)Integer.parseInt(arr[0]))*Math.floor((double)Integer.parseInt(arr2[1])/(double)Integer.parseInt(arr[1])));
        return c;

    }
}
