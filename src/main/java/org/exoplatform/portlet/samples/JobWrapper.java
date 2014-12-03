package org.exoplatform.portlet.samples;

import java.util.List;


import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * Created by IntelliJ IDEA.
 * User: ali
 * Date: 02/12/14
 * Time: 12:35
 */
public class JobWrapper {
  private JobDetail jobDetail;
  private Trigger jobTrigger;
  private String properties;

  public JobWrapper() {
  }

  public JobDetail getJobDetail() {
    return this.jobDetail;
  }

  public void setJobDetail(JobDetail jobDetail) {
    this.jobDetail = jobDetail;
  }

  public Trigger getJobTrigger() {
    return jobTrigger;
  }

  public void setJobTrigger(Trigger jobTrigger) {
    this.jobTrigger = jobTrigger;
  }

  public String getProperties() {
    return properties;
  }

  public void setProperties(String properties) {
    this.properties = properties;
  }
}
