package kr.co.doublechain.exp.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.doublechain.exp.controller.ChatController;


@Component
public class SampleScheduleJob {
	
	private final Logger LOG = LoggerFactory.getLogger(SampleScheduleJob.class);
	
	@Autowired
	ChatController chatController;

    @Scheduled(cron = "*/3 * * * * *")
    public void sampleScheduleJob() throws Exception {
       LOG.info("=============== this is schedule start!!! ===============");
       
       // event 발생 
       chatController.greeting();
       
       LOG.info("=============== this is schedule end!!! ===============");
	}
}
