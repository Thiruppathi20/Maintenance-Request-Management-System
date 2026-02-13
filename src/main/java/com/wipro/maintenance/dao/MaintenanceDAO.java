package com.wipro.maintenance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wipro.maintenance.bean.MaintenanceBean;
import com.wipro.maintenance.util.DBUtil;

public class MaintenanceDAO {
    public String generateRequestID(String name, java.util.Date date) throws Exception {

        String requestId = "";
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String datePart = sdf.format(date);
        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT MAINTENANCE_SEQ.NEXTVAL FROM DUAL")) {

            if (rs.next()) {
                int seq = rs.getInt(1);

                // First 2 letters of name (safe check)
                String prefix = name.length() >= 2 ?
                        name.substring(0, 2).toUpperCase() :
                        name.toUpperCase();

                requestId = datePart + prefix + seq;
            }
        }

        return requestId;
    }
    public boolean recordExists(String name, java.util.Date date) throws Exception {

        boolean exists = false;

        String sql = "SELECT 1 FROM MAINTENANCE_TB " +
                     "WHERE REQUESTERNAME=? AND REQUESTDATE=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDate(2, new java.sql.Date(date.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        }

        return exists;
    }
    public String createRecord(MaintenanceBean bean) throws Exception {

        String sql = "INSERT INTO MAINTENANCE_TB " +
                     "(REQUESTID, REQUESTERNAME, ISSUETYPE, REQUESTDATE, PRIORITY, REMARKS) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bean.getRequestId());
            ps.setString(2, bean.getRequesterName());
            ps.setString(3, bean.getIssueType());
            ps.setDate(4, new java.sql.Date(bean.getRequestDate().getTime()));
            ps.setString(5, bean.getPriority());
            ps.setString(6, bean.getRemarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return bean.getRequestId();
            } else {
                return "FAIL";
            }
        }
    }
    public MaintenanceBean fetchRecord(String name, java.util.Date date) throws Exception {

        MaintenanceBean bean = null;

        String sql = "SELECT * FROM MAINTENANCE_TB " +
                     "WHERE REQUESTERNAME=? AND REQUESTDATE=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDate(2, new java.sql.Date(date.getTime()));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    bean = new MaintenanceBean();

                    bean.setRequestId(rs.getString("REQUESTID"));
                    bean.setRequesterName(rs.getString("REQUESTERNAME"));
                    bean.setIssueType(rs.getString("ISSUETYPE"));
                    bean.setRequestDate(rs.getDate("REQUESTDATE"));
                    bean.setPriority(rs.getString("PRIORITY"));
                    bean.setRemarks(rs.getString("REMARKS"));
                }
            }
        }

        return bean;
    }
    public List<MaintenanceBean> fetchAllRecords() throws Exception {

        List<MaintenanceBean> list = new ArrayList<>();

        String sql = "SELECT * FROM MAINTENANCE_TB ORDER BY REQUESTDATE DESC";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                MaintenanceBean bean = new MaintenanceBean();

                bean.setRequestId(rs.getString("REQUESTID"));
                bean.setRequesterName(rs.getString("REQUESTERNAME"));
                bean.setIssueType(rs.getString("ISSUETYPE"));
                bean.setRequestDate(rs.getDate("REQUESTDATE"));
                bean.setPriority(rs.getString("PRIORITY"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }
        }

        return list;
    }
}
