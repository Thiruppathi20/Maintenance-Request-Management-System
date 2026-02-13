package com.wipro.maintenance.service;

import java.util.*;

import com.wipro.maintenance.bean.MaintenanceBean;
import com.wipro.maintenance.dao.MaintenanceDAO;

public class Administrator {

    MaintenanceDAO dao = new MaintenanceDAO();

    public String addRecord(MaintenanceBean bean) {

        try {

            if (bean == null || bean.getRequesterName() == null ||
                bean.getIssueType() == null || bean.getRequestDate() == null)
                return "INVALID INPUT";

            if (bean.getRequesterName().length() < 2)
                return "INVALID REQUESTER NAME";

            if (bean.getIssueType().length() < 2)
                return "INVALID ISSUE TYPE";

            if (bean.getPriority().length() < 2)
                return "INVALID PRIORITY";

            if (dao.recordExists(bean.getRequesterName(), bean.getRequestDate()))
                return "ALREADY EXISTS";

            String id = dao.generateRequestID(bean.getRequesterName(), bean.getRequestDate());
            bean.setRequestId(id);

            return dao.createRecord(bean);

        } catch (Exception e) {
        	e.printStackTrace();
            return e.getMessage();
        }
    }

    public MaintenanceBean viewRecord(String name, Date date) {
        try {
            return dao.fetchRecord(name, date);
        } catch (Exception e) {
            return null;
        }
    }

    public List<MaintenanceBean> viewAllRecords() {
        try {
            return dao.fetchAllRecords();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
