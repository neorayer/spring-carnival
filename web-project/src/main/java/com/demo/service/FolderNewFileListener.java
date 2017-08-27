package com.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.regex.Pattern;

@Slf4j
@Component
public class FolderNewFileListener implements MessageListener{

    private String dirPath = "c:\\tmp\\testListener";

    //public FolderNewFileListener(String dirPath) {
    //    this.dirPath = dirPath;
   // }

    @Override
    public String listen() throws Exception {
        File[] files = Paths.get(dirPath).toFile().listFiles();
        return files.length > 0 ? files[0].getPath() : null;
    }

    @Override
    public void stop() {

    }
}
