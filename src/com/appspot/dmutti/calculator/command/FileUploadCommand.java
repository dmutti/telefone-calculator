package com.appspot.dmutti.calculator.command;

import com.appspot.dmutti.calculator.support.*;

public class FileUploadCommand {
    
    private StreamingMultipartFile file;

    public StreamingMultipartFile getFile() {
        return this.file;
    }

    public void setFile(StreamingMultipartFile file) {
        this.file = file;
    }
}
