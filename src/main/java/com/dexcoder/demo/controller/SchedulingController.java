package com.dexcoder.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dexcoder.demo.model.Scheduling;
import com.dexcoder.demo.service.SchedulingService;

@Controller
public class SchedulingController {

    @Autowired SchedulingService scheduleJobService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "redirect:list-schedule-job";
    }
    
    @RequestMapping(value = "input-schedule-job", method = RequestMethod.GET)
    public String input(Scheduling scheduleJobVo, Model model) {
        if (scheduleJobVo.getSchedulingId() != null) {
        	Scheduling scheduleJob = scheduleJobService.get(scheduleJobVo.getSchedulingId());
//            scheduleJob.setKeywords(scheduleJobVo.getKeywords());
            model.addAttribute("scheduleJobVo", scheduleJob);
        }
        return "input-schedule-job";
    }

    @RequestMapping(value = "delete-schedule-job", method = RequestMethod.GET)
    public String delete(Long scheduleJobId) {
        scheduleJobService.delete(scheduleJobId);
        return "redirect:list-schedule-job";
    }

    @RequestMapping(value = "run-once-schedule-job", method = RequestMethod.GET)
    public String run(Long scheduleJobId) {
        scheduleJobService.runOnce(scheduleJobId);
        return "redirect:list-schedule-job";
    }

    @RequestMapping(value = "pause-schedule-job", method = RequestMethod.GET)
    public String pause(Long scheduleJobId) {
        scheduleJobService.pauseJob(scheduleJobId);
        return "redirect:list-schedule-job";
    }

    @RequestMapping(value = "resume-schedule-job", method = RequestMethod.GET)
    public String resume(Long scheduleJobId) {
        scheduleJobService.resumeJob(scheduleJobId);
        return "redirect:list-schedule-job";
    }

    @RequestMapping(value = "save-schedule-job", method = RequestMethod.POST)
    public String save(Scheduling scheduleJobVo) {
        scheduleJobVo.setStatus("1");
        if (scheduleJobVo.getSchedulingId() == null) {
            scheduleJobService.insert(scheduleJobVo);
//        } else if (StringUtils.equalsIgnoreCase(scheduleJobVo.getKeywords(),"delUpdate")){
//            scheduleJobService.delUpdate(scheduleJobVo);
//        }else {
            scheduleJobService.update(scheduleJobVo);
        }
        return "redirect:list-schedule-job";
    }

    @RequestMapping(value = "list-schedule-job", method = RequestMethod.GET)
    public String list(Scheduling scheduleJobVo, Model model) {
        model.addAttribute("scheduleJobVoList", scheduleJobService.queryList(scheduleJobVo));
        model.addAttribute("executingJobList", scheduleJobService.queryExecutingJobList());
        return "list-schedule-job";
    }

}
