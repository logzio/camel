/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.aws.s3;

import com.amazonaws.services.s3.AmazonS3;

/**
 * The AWS S3 component configuration properties
 */
public class S3Configuration implements Cloneable {

    private String accessKey;
    private String secretKey;
    private AmazonS3 amazonS3Client;

    private String bucketName;
    private String fileName;
    private String prefix;
    private String region;
    private boolean deleteAfterRead = true;
    private boolean deleteAfterWrite;
    private boolean multiPartUpload;
    private long partSize = 25 * 1024 * 1024;
    private String amazonS3Endpoint;
    private String policy;
    private String storageClass;

    private String marker; // start offset marker
    private int maxConnections;

    public long getPartSize() {
        return partSize;
    }

    public void setPartSize(long partSize) {
        this.partSize = partSize;
    }

    public boolean isMultiPartUpload() {
        return multiPartUpload;
    }

    public void setMultiPartUpload(boolean multiPartUpload) {
        this.multiPartUpload = multiPartUpload;
    }

    public void setAmazonS3Endpoint(String amazonS3Endpoint) {
        this.amazonS3Endpoint = amazonS3Endpoint;
    }

    public String getAmazonS3Endpoint() {
        return amazonS3Endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public AmazonS3 getAmazonS3Client() {
        return amazonS3Client;
    }

    public void setAmazonS3Client(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isDeleteAfterRead() {
        return deleteAfterRead;
    }

    public void setDeleteAfterRead(boolean deleteAfterRead) {
        this.deleteAfterRead = deleteAfterRead;
    }

    public boolean isDeleteAfterWrite() {
        return deleteAfterWrite;
    }

    public void setDeleteAfterWrite(boolean deleteAfterWrite) {
        this.deleteAfterWrite = deleteAfterWrite;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    @Override
    public String toString() {
        return "S3Configuration{" +
                "accessKey='" + accessKey + '\'' +
                ", amazonS3Client=" + amazonS3Client +
                ", bucketName='" + bucketName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", prefix='" + prefix + '\'' +
                ", region='" + region + '\'' +
                ", deleteAfterRead=" + deleteAfterRead +
                ", deleteAfterWrite=" + deleteAfterWrite +
                ", multiPartUpload=" + multiPartUpload +
                ", partSize=" + partSize +
                ", amazonS3Endpoint='" + amazonS3Endpoint + '\'' +
                ", policy='" + policy + '\'' +
                ", storageClass='" + storageClass + '\'' +
                ", marker='" + marker + '\'' +
                ", maxConnections=" + maxConnections +
                '}';
    }
}