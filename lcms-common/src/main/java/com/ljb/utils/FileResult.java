package com.ljb.utils;

import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/18<br>
 * 描述: <br>
 */
public class FileResult {
    public static final int  FileTypeError=0;
    public static final int  FileSizeError=1;
    public static final int  FileReadError=2;
    public static final int  Success=3;
    public Integer msg;
    public List<?> list;

    public static String getMessage(Integer num) {
        String messgae;
        switch (num) {
            case FileTypeError:
                messgae = "不支持的文件格式";
                break;
            case FileSizeError:
                messgae = "文件太大，无法使用，请检查";
                break;
            case FileReadError:
                messgae = "文件读取异常，请检查后重新上传";
                break;
            case Success:
                messgae = "文件处理成功";
                break;
            default:
                messgae = "文件上传发生异常，请稍后重试";
                break;
        }
        return messgae;
    }

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
