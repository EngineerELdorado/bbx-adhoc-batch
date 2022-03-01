package org.epo.dops.bbx.bbxadhocbatch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * PublicationInput.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PublicationInput {

  public static final String DATE_PATTERN = "yyyyMMdd";
  
  /** Reserve */
  private String reserve;
  
  /** The publication country / authority code. */
  private String authorityCode;

  /** The publication sequence number. */
  private String sequenceNumber;
  
  /** The publication kind code. */
  private String kindCode;

  /** The key sequence String */
  private String keySequenceString;

  /** The key sequence numeric */
  private Short keySequence;

  /** The new publication date. */
  private String newPubDateString;
  
  /** The new pub date (LocalDate). */
  private LocalDate newPubDate;

  /**
   * The input line, can be used in the rejected messages.
   */
  private String inputLine;

  /**
   * Return reserve + authorityCode + sequenceNumber + kindCode + keySequenceString 
   * @return pub id
   */
  public String getPubId() {
    return reserve + authorityCode + sequenceNumber + kindCode + keySequenceString;
  }
}
