package com.systems.concurrent.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.util.Rotation;

import com.systems.concurrent.ejb.dao.ProjectDao;
import com.systems.concurrent.ejb.dao.UserDao;
import com.systems.concurrent.ejb.dto.ProjectData;
import com.systems.concurrent.ejb.dto.TaskData;
import com.systems.concurrent.ejb.dto.UserData;

@WebServlet(
		urlPatterns = { "/chart" }
		)
public class ChartServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5515292210991491280L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		 JFreeChart chart=null;
		Stats stats=null;
		String id = req.getParameter("id");
		Long projectId=null;
		if(id!=null && !"".equals(id)){
			try{
				projectId=Long.parseLong(id);
			}catch(Exception e){}
			if(projectId==null)
				return;
			int opened;
			int closed;
			int inprogress;
			int verified;
			ProjectData project=ProjectDao.getInstance().getItem(projectId);
			if(project!=null ){
					opened=0;
					closed=0;
				    inprogress=0;
					verified=0;
					if(project.getTasks()!=null && project.getTasks().size()>0){
						for(TaskData task: project.getTasks()){
							if(TaskData.Status.CLOSED.equals(task.getStatus()))
									closed++;
							else if(TaskData.Status.OPEN.equals(task.getStatus()))
									opened++;
							else if(TaskData.Status.IN_PROGRESS.equals(task.getStatus()))
								inprogress++;
							else if(TaskData.Status.VERIFICATION.equals(task.getStatus()))
								verified++;
						}
					}
					stats=new Stats(project.getName(),opened,closed,inprogress,verified);
				
				
				
			final DefaultPieDataset data = new DefaultPieDataset();
		
			data.setValue("CLOSED",stats.closed);
			data.setValue("IN PROGRESS",stats.inprogress);
			data.setValue("OPEN",stats.opened);
			data.setValue("VERIFICATION",stats.verified);
			
	        // create the chart...
	         chart = ChartFactory.createPieChart3D(
	            stats.projectName,  // chart title
	            data,                   // data
	            true,                   // include legend
	            true,
	            false
	        );

	        chart.setBackgroundPaint(Color.WHITE);
	        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
	        plot.setStartAngle(270);
	        plot.setDirection(Rotation.ANTICLOCKWISE);
	        plot.setForegroundAlpha(0.60f);
	        plot.setInteriorGap(0.33);
		//JFreeChart chart;
		int width = 1200; 
	    int height = 640; 
		
	        //chart=createChart();
	        BufferedImage img = new BufferedImage(width ,height,BufferedImage.TYPE_INT_RGB); 
	        Graphics2D g2 = img.createGraphics(); 
	        chart.draw(g2, new Rectangle2D.Double(0, 0, width, height)); 
	        response.setContentType("image/jpeg");
	        OutputStream out = response.getOutputStream();
	        ImageIO.write(img, "jpg", out);
	        out.close();
		}	
	  }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	/*JFreeChart createChart(){
			final TimeSeriesCollection dataset = new TimeSeriesCollection();
			

			TimeSeries createdBugs = new TimeSeries("Task created");
			TimeSeries fixedBugs = new TimeSeries("Task closed");
			Calendar c = Calendar.getInstance(); 
		    c.setTime(new Date()); 
		    c.add(Calendar.DATE, -60);
		    Random rand=new Random();
		    int a=0,b=0,temp=0;
		    for(int i=1;i<60 ;i++){
		    	a=rand.nextInt(10)+a;
		    	do{
		    		temp=rand.nextInt(10)+b;
		    	}while(temp>a);
		    	b=temp;
		    	createdBugs.add(new Day(c.getTime()),a);
		    	fixedBugs.add(new Day(c.getTime()),b);
		    	c.add(Calendar.DATE, 1);
			}
		  
		    dataset.addSeries(createdBugs);
	        dataset.addSeries(fixedBugs);
	     
	      JFreeChart xylineChart = ChartFactory.createTimeSeriesChart(
	         "Bug fixing statistics", 
	         "Date",
	         "Quantity", 
	         dataset,
	         true, true, true);
	      String dateFormat = "yyyy/MM/dd";
	      DateAxis dateAxis =(DateAxis)xylineChart.getXYPlot().getDomainAxis();
	      dateAxis.setDateFormatOverride(new SimpleDateFormat(dateFormat));
	      return xylineChart;
	    
	}*/
	class Stats{
		String projectName;
		int opened;
		int closed;
		int inprogress;
		int verified;
		public Stats(String projectName, int opened, int closed, int inprogress, int verified) {
			super();
			this.projectName = projectName;
			this.opened = opened;
			this.closed = closed;
			this.inprogress = inprogress;
			this.verified = verified;
		}
		
	}
}
