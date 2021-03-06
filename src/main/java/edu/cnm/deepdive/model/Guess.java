package edu.cnm.deepdive.model;

import com.google.gson.annotations.Expose;
import java.util.Date;

/**
 * Encapsulates a single guess against a secret code, along with the outcome of that guess. An
 *  * instance of this class is sent (as JSON) to the web service, with only the {@code tex} field
 *  * set; the web service returns an instance (again as JSON) with the remaining fields set.
 *  *
 */


  public class Guess {

  @Expose
  private String id;

  @Expose
  private Date created;

  @Expose
  private String text;

  @Expose
  private int exactMatches;

  @Expose
  private int nearMatches;

  @Expose
  private boolean solution;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getExactMatches() {
    return exactMatches;
  }

  public void setExactMatches(int exactMatches) {
    this.exactMatches = exactMatches;
  }

  public int getNearMatches() {
    return nearMatches;
  }

  public void setNearMatches(int nearMatches) {
    this.nearMatches = nearMatches;
  }

  public boolean isSolution() {
    return solution;
  }

 public void setSolution(boolean solution) {
    this.solution = solution;
  }
 }
