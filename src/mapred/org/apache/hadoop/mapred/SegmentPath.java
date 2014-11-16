package org.apache.hadoop.mapred;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;

/**
 * A wrapper to contain a regular Path object and offset/length information.
 *
 * This is used in the case where we want to (possibly) refer to only a portion
 * of a given file.
 */
public class SegmentPath {
  private Path path;
  private long offset;
  private long rawLength;
  private long partLength;

  public SegmentPath(Path path, long offset, long rawLength, long partLength) {
    this.path = path;
    this.offset = offset;
    this.rawLength = rawLength;
    this.partLength = partLength;
  }

  public SegmentPath(Path path, FileSystem fs) throws java.io.IOException {
    this.path = path;
    this.offset = 0;
    this.rawLength = fs.getFileStatus(path).getLen();
    this.partLength = fs.getFileStatus(path).getLen();
  }


  public Path getPath() { return path; }
  public long getOffset() { return offset; }
  public long getRawLength() { return rawLength; }
  public long getPartLength() { return partLength; }
}
