package com.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

@Slf4j
@Component
public class FolderNewFileListener implements MessageListener{

    private String dirPath = "c:\\tmp\\testListener";

    private boolean isStopped = true;

//    public FolderNewFileListener(String dirPath) {
//        this.dirPath = dirPath;
//    }

    @Override
    public String syncListen() throws Exception {
        Path path = Paths.get(dirPath);
        System.out.println(path.getFileName().toFile().getAbsolutePath());
        while(!isStopped) {
            try {
                File[] files = Paths.get(dirPath).toFile().listFiles();
                if (files.length > 0) {
                    return files[0].getName();
                } else {
                    Thread.sleep(1000);
                    isStopped = true;
                }
            } catch (Exception e) {
                isStopped = true;
                throw e;
            }
        }
        return null;
    }

    @Override
    public void stop() {

    }
}
