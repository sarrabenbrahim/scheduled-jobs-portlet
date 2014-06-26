/******************************************************************************
 * JBoss, a division of Red Hat                                               *
 * Copyright 2008, Red Hat Middleware, LLC, and individual                    *
 * contributors as indicated by the @authors tag. See the                     *
 * copyright.txt in the distribution for a full listing of                    *
 * individual contributors.                                                   *
 *                                                                            *
 * This is free software; you can redistribute it and/or modify it            *
 * under the terms of the GNU Lesser General Public License as                *
 * published by the Free Software Foundation; either version 2.1 of           *
 * the License, or (at your option) any later version.                        *
 *                                                                            *
 * This software is distributed in the hope that it will be useful,           *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU           *
 * Lesser General Public License for more details.                            *
 *                                                                            *
 * You should have received a copy of the GNU Lesser General Public           *
 * License along with this software; if not, write to the Free                *
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA         *
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.                   *
 ******************************************************************************/
package org.exo.portal.portlet.samples;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.services.scheduler.JobSchedulerService;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ScheduledJob extends GenericPortlet {

 public static String JOBS = "jobsList";
 public static String TRIGGERS = "triggerlist";

    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
      JobSchedulerService jobService = getService(JobSchedulerService.class);

      List<Trigger> trs = new ArrayList<Trigger>();
      List<JobDetailImpl> jobs = new ArrayList<JobDetailImpl>();
        try {
          List<JobDetail> temp = jobService.getAllJobs();
          for(JobDetail aJob : temp){
            jobs.add((JobDetailImpl)aJob);
            Trigger[] triggers = jobService.getTriggersOfJob(((JobDetailImpl) aJob).getName(),((JobDetailImpl) aJob).getGroup());
              for (Trigger trigger : triggers) {
                trs.add(trigger);
            }
      }
        } catch (Exception e) {
          e.printStackTrace();
        }
      request.setAttribute(JOBS,jobs);
      PortletRequestDispatcher prd = getPortletContext().getRequestDispatcher("/jsp/jobs.jsp");
      prd.include(request, response);
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

  public static <T> T getService(Class<T> clazz) {
    String containerName = "portal";
    ExoContainer container = ExoContainerContext.getCurrentContainer();
    if (container.getComponentInstanceOfType(clazz)==null) {
      containerName = PortalContainer.getCurrentPortalContainerName();
      container = RootContainer.getInstance().getPortalContainer(containerName);
    }
    return clazz.cast(container.getComponentInstanceOfType(clazz));
  }

}
