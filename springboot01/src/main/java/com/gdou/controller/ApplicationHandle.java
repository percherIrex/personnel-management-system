package com.gdou.controller;

import com.gdou.MyUitls.OperationRecord;
import com.gdou.dao.OperationMapper;
import com.gdou.entity.Application;
import com.gdou.entity.Record;
import com.gdou.service.ApplicationService;
import com.gdou.service.RecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationHandle {

    @Resource
    private ApplicationService applicationService;
    @Resource
    private OperationMapper operationMapper;
    @Resource
    private RecordService recordService;

    @GetMapping("/handle")
    public List<Application> listNotHandle() {
        return applicationService.listByHandle();
    }

    @PutMapping("/{way}/{main_id}/{main_name}")
    public boolean handleCallBack(@RequestBody Application app,
                                  @PathVariable("way") Integer way,
                                  @PathVariable("main_id") String main_id,
                                  @PathVariable("main_name") String main_name)
    {
        String main_user = main_id + "-" + main_name;
        String operate = "";
        app.setHandle(true);
        app.setApprover(main_user);

        //同意请假
        if (way == 1) {
            app.setAllow(true);
            operate = "批准请假";

            Record record = new Record();
            record.setName(app.getName());
            record.setUid(app.getUid());
            record.setReason(app.getReason());
            record.setStatus(false);
            record.setDate(app.getDate());

            recordService.save(record);

        } else {
            app.setAllow(false);
            operate = "拒绝请假";
        }
        //操作行为记录
        operationMapper.insert(OperationRecord.init(main_user, operate, app.getUid() + "-" + app.getName()));

        return applicationService.updateById(app);
    }

    @GetMapping("/{uid}")
    public List<Application> PersonnelList(@PathVariable("uid") Integer uid) {
        return applicationService.listByUid(uid);
    }

    @PostMapping("/{is_author}")
    public boolean NewApplication(@RequestBody Application app,
                                  @PathVariable("is_author") boolean is_author) {
        Application application = applicationService.is_Ex(app.getUid(), app.getDate());

        if (application != null) {
            return false;
        }

        if (is_author) {
            app.setHandle(true);
            app.setAllow(true);
            app.setApprover(app.getUid() + "-" + app.getName());

            //把记录添加到考勤表
            Record record = new Record();
            record.setName(app.getName());
            record.setUid(app.getUid());
            record.setReason(app.getReason());
            record.setStatus(false);
            record.setDate(app.getDate());

            recordService.save(record);
        }
        
        return applicationService.save(app);
    }
}
