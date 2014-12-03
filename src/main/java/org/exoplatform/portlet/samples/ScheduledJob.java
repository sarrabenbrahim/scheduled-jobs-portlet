
package org.exoplatform.portlet.samples;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.services.scheduler.JobSchedulerService;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;

import javax.portlet.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ScheduledJob extends GenericPortlet {

  public static String JOBS = "jobsList";
  public static String TRIGGERS = "triggerlist";
  public static String SCHEDULER = "scheduler";

  public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    JobSchedulerService jobService = getService(JobSchedulerService.class);

    List<Trigger> trs = new ArrayList<Trigger>();
    Trigger[] triggers = new Trigger[0];
    Scheduler scheduler = null;
    List<JobWrapper> jobs = new ArrayList<JobWrapper>();
    try {
      List<JobDetail> temp = jobService.getAllJobs();
      for (JobDetail aJob : temp) {
        JobWrapper wrapper = new JobWrapper();
        wrapper.setJobDetail((JobDetailImpl) aJob);
        String properties = "";
        for(String key : aJob.getJobDataMap().getKeys()){
          properties+=key + " : " +aJob.getJobDataMap().get(key)+" - ";
        }
        wrapper.setProperties(properties);
        triggers = jobService.getTriggersOfJob(((JobDetailImpl) aJob).getName(), ((JobDetailImpl) aJob).getGroup().split(":")[1]);
        //for (Trigger trigger : triggers) {
          //trs.add(trigger);
        //}
        //wrapper.setJobTrigger(trs);
        if(triggers[0]!=null) {
          wrapper.setJobTrigger(triggers[0]);
        }
        jobs.add(wrapper);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute(JOBS, jobs);
    PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/jobs.jsp");
    prd.include(request, response);
  }


  public static <T> T getService(Class<T> clazz) {
    String containerName;
    ExoContainer container = ExoContainerContext.getCurrentContainer();
    if (container.getComponentInstanceOfType(clazz) == null) {
      containerName = PortalContainer.getCurrentPortalContainerName();
      container = RootContainer.getInstance().getPortalContainer(containerName);
    }
    return clazz.cast(container.getComponentInstanceOfType(clazz));
  }

  protected void doHelp(RenderRequest rRequest, RenderResponse rResponse) throws PortletException, IOException {
    rResponse.setContentType("text/html");
    PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/help.jsp");
    prd.include(rRequest, rResponse);
  }

  protected void doEdit(RenderRequest rRequest, RenderResponse rResponse) throws PortletException, IOException {
    rResponse.setContentType("text/html");
    PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/edit.jsp");
    prd.include(rRequest, rResponse);
  }
}
