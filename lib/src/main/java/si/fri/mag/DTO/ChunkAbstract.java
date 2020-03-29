package si.fri.mag.DTO;

public abstract class ChunkAbstract {
    protected String awsBucketName;
    protected String awsStorageName;
    protected Double length;

    public String getAwsStorageName() {
        return awsStorageName;
    }

    public String getAwsBucketName() {
        return awsBucketName;
    }

    public Double getLength() {
        return length;
    }

    // setters

    public void setAwsStorageName(String awsStorageName) {
        this.awsStorageName = awsStorageName;
    }

    public void setAwsBucketName(String awsBucketName) {
        this.awsBucketName = awsBucketName;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}
