package io.weekendprojects.musicplayer.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StorageService {

  @Value("${application.bucket.name}")
  private String bucketName;

  private final AmazonS3 s3Client;

  public String uploadSongFile(MultipartFile file) throws IOException {
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentType(file.getContentType());

    s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
        .withCannedAcl(CannedAccessControlList.Private));

    return fileName + " has been uploaded.";
  }

  public List<String> getSongFileNames() {
    ListObjectsV2Result result = s3Client.listObjectsV2("music-data-storage");
    List<S3ObjectSummary> objectSummaries = result.getObjectSummaries();

    return objectSummaries.stream()
        .map(S3ObjectSummary::getKey)
        .toList();
  }


}
