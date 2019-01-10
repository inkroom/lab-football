package cn.edu.nsu.lib.services.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceManager {
    /**
     * 将所有的service通过spring注解在此类中，通过set和get方法调用
     */
    @Autowired
    private ITeacherService teacherService;

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
