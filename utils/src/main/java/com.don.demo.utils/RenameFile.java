package com.don.demo.utils;

import com.don.demo.utils.work.Md5Util;

import java.io.File;

/**
 * @author Carl Don
 * @version V1.0
 * @ClassName utils.RenameFile
 * @date 2019年04月24日 下午 6:59
 */

/**
 * 重命名源文件名字有规律的文件（右边完全一致的文件）
 * 规则：左右限定+中间提取n位
 */

public class RenameFile {
    public  void renameFile() {

        //字幕文件所在目录
        String sourceDir = "D:\\电视剧\\How.I.Met.Your.Mothe\\How I Met Your Mother S01";
        //所有字幕文件都有的共有的也有的特征部分，对这些字幕文件操作
        String tag = "How I Met Your Mother";
        //旧的字幕文件模板，随便选一个
        String firstWordTemplate = "How I Met Your Mother S01E01.srt";
        //视频文件的模板,随便选一个就可以
        String vedioNameTemplate = "How.I.Met.Your.Mother.S01E01.1080p.WEB-DL.DD5.1.H.264.mkv";
        //字幕和视频的季度集数如何表示，以第一季第一集为例子
        String vedioNum = "S01E01";

        //新文件名 : 左边permanentLeft + 保留的季数集数(自动获取) + 右边permanentRight + 字幕格式
        //  How.I.Met.Your.Mother.S01E01.1080p.WEB-DL.DD5.1.H.264.mkv   How I Met Your Mother S01E01.srt
        //"How.I.Met.Your.Mother."  +  S01E01  +  ".1080p.WEB-DL.DD5.1.H.264." +  "srt"

        //剪除文件后缀
        String newVedioNameTemplate = vedioNameTemplate.substring(0, vedioNameTemplate.lastIndexOf("."));
        //季度集数长度
        int vedioNumLength = vedioNum.length();
        // 想要生成的字幕文件名的左边，季度集数左边部分
        String part1 = newVedioNameTemplate.substring(0, newVedioNameTemplate.indexOf(vedioNum));
        String part2 = "";
        String part3 = newVedioNameTemplate.substring(newVedioNameTemplate.indexOf(vedioNum) + vedioNumLength);
        String part4 = firstWordTemplate.substring(firstWordTemplate.lastIndexOf("."));
        LockWindow.sdf sdf = new LockWindow<>().new sdf();
        //part2index
        int part2Index = firstWordTemplate.indexOf(vedioNum);

        File file1 = new File(sourceDir);
        int num = 0;
        System.out.println("开始规律重命名文件");
        if (file1.isDirectory()) {
            String[] strings = file1.list();

            for (String name : strings) {
                part2 = name.substring(part2Index, part2Index + vedioNumLength);

                if (name.contains(tag)) {
                    System.out.println(name);
                    if (
                            new File(sourceDir + "\\" + name).renameTo(new File((sourceDir + "\\"
                                    + part1 + part2 + part3 + part4)))) {
                        num++;
                        System.out.println("成功重命名第" + num + "个文件");
                    }

                }
            }
        }
        System.out.println("一共重命名" + num + "次");
    }

    public static void main(String[] args) {
        String sdf = Md5Util.getMD5("sdf");
        System.out.println(sdf);
    }
}
