
package org.jboss.portal.portlet.samples;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.services.scheduler.JobSchedulerService;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;


import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ScheduledJob extends GenericPortlet {




    public static String JOBS = "jobsList";
    public static String TRIGGERS = "triggerlist";
    public static String SCHEDULER = "scheduler";
    public Scheduler scheduler;


    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        JobSchedulerService jobService = getService(JobSchedulerService.class);


        List<Trigger> trs = new ArrayList<Trigger>();
        List<JobDetailImpl> jobs = new ArrayList<JobDetailImpl>();
        Trigger[] triggers = new Trigger[0];
        try {
            List<JobDetail> temp = jobService.getAllJobs();
            for (JobDetail aJob : temp) {
                jobs.add((JobDetailImpl) aJob);
                triggers = jobService.getTriggersOfJob(((JobDetailImpl) aJob).getName(),((JobDetailImpl) aJob).getGroup().split(":")[1]);
                Collections.addAll(trs, triggers);
                scheduler.triggerJob(aJob.getKey());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        request.setAttribute(JOBS, jobs);
        request.setAttribute(TRIGGERS, triggers);
        request.setAttribute(SCHEDULER, scheduler);
        PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/jobs.jsp");
        prd.include(request, response);

    }


    public static <T> T getService(Class<T> clazz) {
        String containerName = "portal";
        ExoContainer container = ExoContainerContext.getCurrentContainer();
        if (container.getComponentInstanceOfType(clazz)==null) {
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