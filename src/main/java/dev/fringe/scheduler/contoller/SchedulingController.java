package dev.fringe.scheduler.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.fringe.scheduler.model.Scheduling;
import dev.fringe.scheduler.service.SchedulingService;

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
        model.addAttribute("title", "작업을 추가/수정합니다.");
        return "input-schedule-job";
    }

    @RequestMapping(value = "delete-schedule-job", method = RequestMethod.GET)
    public String delete(Long schedulingId) {
        scheduleJobService.delete(schedulingId);
        return "redirect:list-schedule-job";
    }

    @RequestMapping(value = "run-once-schedule-job", method = RequestMethod.GET)
    public String run(Long schedulingId) {
        scheduleJobService.runOnce(schedulingId);
        return "redirect:list-schedule-job";
    }

    @RequestMapping(value = "pause-schedule-job", method = RequestMethod.GET)
    public String pause(Long schedulingId) {
        scheduleJobService.pauseJob(schedulingId);
        return "redirect:list-schedule-job";
    }

    @RequestMapping(value = "resume-schedule-job", method = RequestMethod.GET)
    public String resume(Long schedulingId) {
        scheduleJobService.resumeJob(schedulingId);
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
        model.addAttribute("title", "작업 목록");
        return "list-schedule-job";
    }

}
