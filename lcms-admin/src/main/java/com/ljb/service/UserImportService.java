package com.ljb.service;

import com.ljb.utils.FileResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/18<br>
 * 描述: <br>
 */
public interface UserImportService {
    FileResult importByExcel(MultipartFile file);
}
