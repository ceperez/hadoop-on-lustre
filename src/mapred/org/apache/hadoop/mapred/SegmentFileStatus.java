package org.apache.hadoop.mapred;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.SegmentPath;

/**
 * A wrapper to contain a regular FileStatus object and offset/length
 * information.
 *
 * This is used in the case where we want to (possibly) refer to only a portion
 * of a given file.
 */
public class SegmentFileStatus {
  private FileStatus status;
  private long offset;
  private long rawLength;
  private long partLength;

  public SegmentFileStatus(FileStatus status, long offset, long rawLength, long partLength) {
    this.status = status;
    this.offset = offset;
    this.rawLength = rawLength;
    this.partLength = partLength;
  }

  public FileStatus getFileStatus() { return status; }
  public long getOffset() { return offset; }
  public long getRawLength() { return rawLength; }
  public long getPartLength() { return partLength; }

  public SegmentPath getSegmentPath() {
    return new SegmentPath(status.getPath(), offset, rawLength, partLength);
  }
}
