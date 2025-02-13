package ohtuminiprojekti.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import ohtuminiprojekti.dao.VideoRepository;
import ohtuminiprojekti.domain.Link;
import ohtuminiprojekti.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
  @Autowired
  private VideoRepository videoRepository;

  public Video newVideo(String name, String url, String comment) {
    Video video = new Video();
    video.setName(name);
    video.setLink(url);
    video.setComment(comment);
    video.setHasBeenRead(false);
    videoRepository.save(video);
    return video;
  }

  public Video getById(long id) {
    return videoRepository.getOne(id);
  }
  
  public boolean isURL(String url) {
    try {
     (new java.net.URL(url)).openStream().close();
     return true;
    } catch (Exception ex) { }
    return false;
  }

  public boolean existingVideoByUrl(String url) {
    return videoRepository.findByLink(url) != null;
  }

  public void edit(long id, String name, String url, String comment) {
    Video video = videoRepository.getOne(id);
    video.setLink(url);
    video.setName(name);
    video.setComment(comment);
    videoRepository.save(video);
  }

  public String getTitleOfUrl(String url) {
    String title = "";
    InputStream response = null; 
    try {
      response = new URL(url).openStream();
      Scanner scanner = new Scanner(response);
      String responseBody = scanner.useDelimiter("\\A").next();
      title = responseBody.substring(responseBody.indexOf("<title>") + 7, responseBody.indexOf("</title>"));
      response.close();
    } catch (IOException ex) {
    } 
    return title;
  } 
}
