package com.ljb.service.impl;

import com.ljb.dao.RoleDao;
import com.ljb.dao.UserDao;
import com.ljb.dao.UserRoleDao;
import com.ljb.entity.Role;
import com.ljb.entity.User;
import com.ljb.entity.UserRole;
import com.ljb.security.IdentityUtils;
import com.ljb.service.UserImportService;
import com.ljb.utils.Condition;
import com.ljb.utils.Constant;
import com.ljb.utils.FileResult;
import com.ljb.utils.SHA256Util;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/18<br>
 * 描述: <br>
 */
@Service
public class UserImportServiceImpl implements UserImportService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public FileResult importByExcel(MultipartFile file) {
        FileResult fileResult=new FileResult();
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (file.getSize() > Constant.EXCELSIZE) {
            fileResult.setMsg(FileResult.FileSizeError);
            return fileResult;
        }
        Workbook workbook = null;
        try {
            if (fileType.equals("xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (fileType.equals("xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else {
                fileResult.setMsg(FileResult.FileTypeError);
                return fileResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet=workbook.getSheetAt(0);
        List<User> users=new ArrayList<>();
        List<User> unRegisterUsers=new ArrayList<>();
        boolean isError=false;
        for(int i=sheet.getFirstRowNum()+1;i<sheet.getPhysicalNumberOfRows();i++){
            try {
                User user = userDao.findByCode(transString(sheet.getRow(i).getCell(0)));
                if(user==null) {
                    try {
                        user=new User();
                        user.setCode(transString(sheet.getRow(i).getCell(0)));
                        user.setUsername(sheet.getRow(i).getCell(1).toString());
                        user.setPassword(SHA256Util.getSHA256Str(transString(sheet.getRow(i).getCell(2))));
                        user.setPhone(transString(sheet.getRow(i).getCell(3)));
                        user.setEmail(transString(sheet.getRow(i).getCell(4)));
                        user.setStatus(1);
                        user.setIsDelete(0);
                        user.setCreateId(IdentityUtils.getUserId());
                        unRegisterUsers.add(user);
                    }catch (Exception e){

                    }
                }else{
                    users.add(user);
                }
            }catch (NullPointerException e){
                isError=true;
                break;
            }
        }
        if(isError) {
            fileResult.setMsg(FileResult.FileReadError);
            return fileResult;
        }
        if(unRegisterUsers.size()>0){
            userDao.saveBatch(unRegisterUsers);
            users.addAll(unRegisterUsers);
            List<UserRole> userRoles = new ArrayList<>();
            List<Role> roles= roleDao.selectByMap(Condition.build().eq("name", Constant.MEMBER_ROLE));
            if(roles.size()>0) {
                for (User user : unRegisterUsers) {
                    if(userRoleDao.selectByMap(Condition.build().eq("role_id", roles.get(0).getId()).eq("user_id",user.getId())).size()<=0) {
                        UserRole userRole = new UserRole();
                        if (IdentityUtils.isAuthenticated()) {
                            userRole.setCreateId(IdentityUtils.getUserId());
                        } else {
                            userRole.setCreateId(user.getId());
                        }
                        userRole.setUserId(user.getId());
                        userRole.setRoleId(roles.get(0).getId());
                        userRoles.add(userRole);
                    }
                }
            }
            if(userRoles.size()>0) {
                userRoleDao.saveBatch(userRoles);
            }
        }
        fileResult.setMsg(FileResult.Success);
        fileResult.setList(users);
        return fileResult;
    }

    public String transString(Cell cell){
        String value;
        try {
            value=cell.toString();
            if(value.indexOf(".")>-1){
                DecimalFormat df = new DecimalFormat("#");//转换成整型
                value=df.format(cell.getNumericCellValue());
            }
        }catch (Exception e){
            value="空";
        }

        return value;
    }
}
