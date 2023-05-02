package com.don.demo.utils;

import java.io.File;

/**
 * 删除两个文件夹中相同的文件,大小相同
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName DeleteRepeatedFile
 * @date 2019年06月23日 上午 9:01
 */
public class DeleteRepeatedFile {
    //删除了几个文件
    private static int deleteNum = 0;

    public void start() {
        //需要操作的文件夹
        String dirPath1 = "D:\\CloudMusic\\all";
        File dir1 = new File(dirPath1);
        File[] files1 = dir1.listFiles();
        //对照的文件夹及其子文件夹
        String dirPath2 = "D:\\CloudMusic";
        File dir2 = new File(dirPath2);
        File[] files2 = dir2.listFiles();
        //遍历,是否都是文件,对照大小和文件名字
        for (int i = 0; i < files1.length; i++) {
            File file1 = files1[i];
            //对参照文件夹进行遍历
            for (int j = 0; j < files2.length; j++) {
                //all外的文件夹
                File file2 = files2[j];

                if (!file2.getName().equals("all")) {
                    //       System.out.println("-------------------"+file2.getName()+"--------------------");
                    deleteFile(file1, file2);
                }
            }
        }
    }

    /**
     * 对两个文件进行操作,把一个文件看作文件,一个文件看作文件夹
     *
     * @param file1
     * @param fileOrDir
     */
    private void deleteFile(File file1, File fileOrDir) {
        //是文件
        //如果是相同的文件,则删除
        if (fileOrDir.isFile()) {
            if (file1.isFile() &&
                    fileOrDir.length() == file1.length() &&
                    fileOrDir.getName().equals(file1.getName())) {
                deleteNum++;
                System.out.println("第" + deleteNum + "个删除的文件是" + file1.getName());
                file1.delete();
            }
        } else {
            //如果fileOrDir是文件夹,迭代自己
            File[] files = fileOrDir.listFiles();
            //   System.out.println("-------------------"+fileOrDir.getName()+"--------------------");
            for (int j = 0; j < files.length; j++) {
                File file2 = files[j];
                deleteFile(file1, file2);
            }
        }

    }
}
